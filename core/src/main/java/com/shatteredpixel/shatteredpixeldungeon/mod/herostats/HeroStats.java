package com.shatteredpixel.shatteredpixeldungeon.mod.herostats;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ArtifactRecharge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barkskin;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Combo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.HoldFast;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Momentum;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MonkEnergy;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.PhysicalEmpower;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Recharging;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.SpiritForm;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.NaturesPower;
import com.shatteredpixel.shatteredpixeldungeon.items.KindOfWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfAccuracy;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfEvasion;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfForce;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfForce.BrawlersStance;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfFuror;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Quarterstaff;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.RoundShield;
import com.shatteredpixel.shatteredpixeldungeon.mod.ModHooks;
import com.shatteredpixel.shatteredpixeldungeon.mod.ModOption;

import java.util.ArrayList;
import java.util.List;

/** Read-only effective-stat calculations; special target-dependent procs are omitted. */
public final class HeroStats implements ModOption {
    @Override public String id() { return "hero-effective-stats"; }
    @Override public String title() { return "英雄有效属性"; }

    @Override public List<ModHooks.HeroStat> heroStats(Hero hero) {
        List<ModHooks.HeroStat> rows = new ArrayList<>();
        KindOfWeapon weapon = hero.belongings.attackingWeapon();
        boolean unarmed = isUnarmed(hero);

        int[] damage = damageRange(hero, weapon, unarmed);
        PhysicalEmpower empower = hero.buff(PhysicalEmpower.class);
        if (empower != null) { damage[0] += empower.dmgBoost; damage[1] += empower.dmgBoost; }
        if (hero.heroClass != HeroClass.DUELIST && hero.hasTalent(Talent.WEAPON_RECHARGING)
                && (hero.buff(Recharging.class) != null || hero.buff(ArtifactRecharge.class) != null)) {
            int talent = hero.pointsInTalent(Talent.WEAPON_RECHARGING);
            damage[0] = Math.round(damage[0] * 1.025f + .025f * talent);
            damage[1] = Math.round(damage[1] * 1.025f + .025f * talent);
        }
        rows.add(row("攻击", damage[0] + "-" + damage[1]));

        int[] dr = physicalDr(hero, weapon, unarmed);
        rows.add(row("防御", dr[0] + "-" + dr[1]));
        rows.add(row("命中", Integer.toString(accuracy(hero, weapon))));
        rows.add(row("闪避", evasion(hero)));
        rows.add(row("速度", format(speed(hero))));
        rows.add(row("延迟", format(delay(hero, weapon, unarmed))));
        Hunger hunger = hero.buff(Hunger.class);
        rows.add(row("饥饿", hunger == null ? "0" : Integer.toString(hunger.hunger())));
        return rows;
    }

    private static int[] damageRange(Hero hero, KindOfWeapon weapon, boolean unarmed) {
        if (!unarmed) return new int[]{weapon.min(), weapon.max()};
        if (hasForce(hero)) {
            int level = RingOfForce.armedDamageBonus(hero);
            float tier = forceTier(hero.STR());
            int bonus = 0;
            BrawlersStance stance = hero.buff(BrawlersStance.class);
            if (stance != null && stance.active)
                bonus = Math.round(3 + tier + level * ((4 + 2 * tier) / 8f));
            return new int[]{Math.max(0, Math.round(tier + level)) + bonus,
                    Math.max(0, Math.round(5 * (tier + 1) + level * (tier + 1))) + bonus};
        }
        return new int[]{1, Math.max(hero.STR() - 8, 1)};
    }

    private static int[] physicalDr(Hero hero, KindOfWeapon weapon, boolean unarmed) {
        int min = 0, max = 0;
        Armor armor = hero.belongings.armor();
        if (armor != null) {
            int penalty = Math.max(0, 2 * (armor.STRReq() - hero.STR()));
            min = Math.max(0, armor.DRMin() - penalty);
            max = Math.max(0, armor.DRMax() - penalty);
        }
        if (weapon != null && !unarmed) {
            int weaponPenalty = weapon instanceof Weapon
                    ? Math.max(0, 2 * (((Weapon) weapon).STRReq() - hero.STR())) : 0;
            int weaponDr = Math.max(0, weapon.defenseFactor(hero) - weaponPenalty);
            max += weaponDr;
        }
        max += Barkskin.currentLevel(hero);
        HoldFast holdFast = hero.buff(HoldFast.class);
        if (holdFast != null && holdFast.pos == hero.pos) {
            int talent = hero.pointsInTalent(Talent.HOLD_FAST);
            min += talent;
            max += 2 * talent;
        }
        return new int[]{min, max};
    }

    private static int accuracy(Hero hero, KindOfWeapon weapon) {
        float result = hero.baseAttackSkill() * RingOfAccuracy.accuracyMultiplier(hero);
        boolean talentAttack = !(weapon instanceof MissileWeapon)
                && (hero.hasTalent(Talent.PRECISE_ASSAULT) || hero.hasTalent(Talent.LIQUID_AGILITY))
                && hero.belongings.abilityWeapon != weapon
                && hero.buff(com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MonkEnergy.MonkAbility.UnarmedAbilityTracker.class) == null;
        if (talentAttack && hero.heroClass != HeroClass.DUELIST && hero.hasTalent(Talent.PRECISE_ASSAULT))
            result *= 1f + .1f * hero.pointsInTalent(Talent.PRECISE_ASSAULT);
        if (talentAttack && hero.buff(Talent.PreciseAssaultTracker.class) != null) {
            int points = hero.pointsInTalent(Talent.PRECISE_ASSAULT);
            result *= points == 3 ? Float.POSITIVE_INFINITY : points == 2 ? 5f : 2f;
        } else if (talentAttack && hero.buff(Talent.LiquidAgilACCTracker.class) != null) {
            result *= hero.pointsInTalent(Talent.LIQUID_AGILITY) == 2 ? Float.POSITIVE_INFINITY : 3f;
        } else if (weapon instanceof MissileWeapon && hero.buff(Momentum.class) != null
                && hero.buff(Momentum.class).freerunning()) {
            result *= 1f + hero.pointsInTalent(Talent.PROJECTILE_MOMENTUM) / 2f;
        }
        if (hero.buff(com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Scimitar.SwordDance.class) != null)
            result *= 1.5f;
        return Math.max(1, Math.round(result));
    }

    private static String evasion(Hero hero) {
        if (hero.buff(Combo.ParryTracker.class) != null
                || hero.buff(RoundShield.GuardTracker.class) != null) return "∞";
        float result = hero.baseDefenseSkill() * RingOfEvasion.evasionMultiplier(hero);
        if (hero.buff(Talent.LiquidAgilEVATracker.class) != null)
            result *= hero.pointsInTalent(Talent.LIQUID_AGILITY) == 2 ? Float.POSITIVE_INFINITY : 3f;
        if (hero.buff(Quarterstaff.DefensiveStance.class) != null) result *= 3f;
        if (hero.paralysed > 0) result /= 2f;
        Armor armor = hero.belongings.armor();
        if (armor != null) result = armor.evasionFactor(hero, result);
        return Integer.toString(Math.max(1, Math.round(result)));
    }

    private static float speed(Hero hero) {
        float result = hero.baseMoveSpeed() * RingOfHaste.speedMultiplier(hero);
        Armor armor = hero.belongings.armor();
        if (armor != null) result = armor.speedFactor(hero, result);
        Momentum momentum = hero.buff(Momentum.class);
        if (momentum != null) result *= momentum.speedMultiplier();
        if (hero.buff(NaturesPower.naturesPowerTracker.class) != null)
            result *= 2f + .25f * hero.pointsInTalent(Talent.GROWING_POWER);
        return com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AscensionChallenge.modifyHeroSpeed(result);
    }

    private static float delay(Hero hero, KindOfWeapon weapon, boolean unarmed) {
        if (hero.buff(Talent.LethalMomentumTracker.class) != null) return 0;
        if (!unarmed && weapon != null) return weapon.delayFactor(hero);
        float result = 1f / RingOfFuror.attackSpeedMultiplier(hero);
        if (hero.buff(com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Scimitar.SwordDance.class) != null)
            result = 1f / (RingOfFuror.attackSpeedMultiplier(hero) + .6f);
        if (weapon instanceof Weapon && hasUnarmedAugment(hero))
            result = ((Weapon) weapon).augment.delayFactor(result);
        return result;
    }

    private static boolean isUnarmed(Hero hero) {
        if (hero.belongings.attackingWeapon() == null
                || hero.buff(com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MonkEnergy.MonkAbility.UnarmedAbilityTracker.class) != null) return true;
        if (hero.belongings.thrownWeapon != null || hero.belongings.abilityWeapon != null) return false;
        BrawlersStance stance = hero.buff(BrawlersStance.class);
        return stance != null && stance.active && hero.buff(RingOfForce.Force.class) != null;
    }

    private static boolean hasForce(Hero hero) {
        return hero.buff(RingOfForce.Force.class) != null
                || hero.buff(SpiritForm.SpiritFormBuff.class) != null
                && hero.buff(SpiritForm.SpiritFormBuff.class).ring() instanceof RingOfForce;
    }

    private static boolean hasUnarmedAugment(Hero hero) {
        return hero.buff(MonkEnergy.MonkAbility.UnarmedAbilityTracker.class) == null
                && hero.buff(BrawlersStance.class) != null
                && hero.buff(BrawlersStance.class).active;
    }

    private static float forceTier(int strength) {
        float tier = Math.max(1, (strength - 8) / 2f);
        return tier > 5 ? 5 + (tier - 5) / 2f : tier;
    }

    private static ModHooks.HeroStat row(String label, String value) { return new ModHooks.HeroStat(label, value); }
    private static String format(float value) { return Float.toString((float) Math.round(value * 100f) / 100f); }
}
