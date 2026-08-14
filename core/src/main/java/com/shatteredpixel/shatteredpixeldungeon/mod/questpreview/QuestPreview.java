package com.shatteredpixel.shatteredpixeldungeon.mod.questpreview;

import com.shatteredpixel.shatteredpixeldungeon.mod.ModOption;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Blacksmith;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Ghost;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Imp;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Wandmaker;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class QuestPreview implements ModOption {
	public String id(){return "quest-preview";} public String title(){return "任务奖励预览";} public boolean defaultEnabled(){return true;} public void onMove(){update();} public void onFloor(){reset();} public void onNewGame(){reset();}

	private static boolean ghostHinted = false;
	private static boolean wandmakerHinted = false;
	private static boolean impHinted = false;
	private static boolean blacksmithHinted = false;

	public static void reset() {
		ghostHinted = false;
		wandmakerHinted = false;
		impHinted = false;
		blacksmithHinted = false;
	}

	private static void update() {
		checkGhostHint();
		checkWandmakerHint();
		checkImpHint();
		checkBlacksmithHint();
	}

	private static void checkGhostHint() {
		if (!isAdjacentTo(Ghost.class)) {
			ghostHinted = false;
			return;
		}
		if (!ghostHinted && Ghost.Quest.weapon != null && Ghost.Quest.armor != null) {
			for (Mob mob : Dungeon.level.mobs) {
				if (mob instanceof Ghost && Dungeon.level.distance(Dungeon.hero.pos, mob.pos) == 1) {
					ghostHinted = true;
					Weapon wep = Ghost.Quest.weapon;
					Armor arm = Ghost.Quest.armor;
					wep.levelKnown = true;
					wep.cursedKnown = true;
					arm.levelKnown = true;
					arm.cursedKnown = true;
					String wepStr = rewardItemName(wep, Ghost.Quest.enchant == null ? "" : Ghost.Quest.enchant.name());
					String armStr = rewardItemName(arm, Ghost.Quest.glyph == null ? "" : Ghost.Quest.glyph.name());
					GLog.w("（任务感应）幽灵奖励预览：武器【" + wepStr + "】 / 防具【" + armStr + "】");
					break;
				}
			}
		}
	}

	private static void checkWandmakerHint() {
		if (!isAdjacentTo(Wandmaker.class)) {
			wandmakerHinted = false;
			return;
		}
		if (!wandmakerHinted && Wandmaker.Quest.wand1 != null && Wandmaker.Quest.wand2 != null) {
			for (Mob mob : Dungeon.level.mobs) {
				if (mob instanceof Wandmaker && Dungeon.level.distance(Dungeon.hero.pos, mob.pos) == 1) {
					wandmakerHinted = true;
					Wandmaker.Quest.wand1.levelKnown = true;
					Wandmaker.Quest.wand1.cursedKnown = true;
					Wandmaker.Quest.wand2.levelKnown = true;
					Wandmaker.Quest.wand2.cursedKnown = true;
					GLog.w("（任务感应）老杖匠奖励预览：【" + rewardItemName(Wandmaker.Quest.wand1) + "】 / 【" + rewardItemName(Wandmaker.Quest.wand2) + "】");
					break;
				}
			}
		}
	}

	private static void checkImpHint() {
		if (!isAdjacentTo(Imp.class)) {
			impHinted = false;
			return;
		}
		if (!impHinted && Imp.Quest.reward != null) {
			for (Mob mob : Dungeon.level.mobs) {
				if (mob instanceof Imp && Dungeon.level.distance(Dungeon.hero.pos, mob.pos) == 1) {
					impHinted = true;
					Imp.Quest.reward.levelKnown = true;
					Imp.Quest.reward.cursedKnown = true;
					GLog.w("（任务感应）小恶魔奖励预览：【" + rewardItemName(Imp.Quest.reward) + "】");
					break;
				}
			}
		}
	}

	private static void checkBlacksmithHint() {
		if (!isAdjacentTo(Blacksmith.class)) {
			blacksmithHinted = false;
			return;
		}
		if (!blacksmithHinted && Blacksmith.Quest.smithRewards != null && !Blacksmith.Quest.smithRewards.isEmpty()) {
			for (Mob mob : Dungeon.level.mobs) {
				if (mob instanceof Blacksmith && Dungeon.level.distance(Dungeon.hero.pos, mob.pos) == 1) {
					blacksmithHinted = true;
					StringBuilder sb = new StringBuilder("（任务感应）铁匠打造装备预览：");
					for (int i = 0; i < Blacksmith.Quest.smithRewards.size(); i++) {
						Item item = Blacksmith.Quest.smithRewards.get(i);
						item.levelKnown = true;
						item.cursedKnown = true;
						if (i > 0) sb.append(" / ");
						String modifier = "";
						if (item instanceof Weapon && Blacksmith.Quest.smithEnchant != null) {
							modifier = Blacksmith.Quest.smithEnchant.name();
						} else if (item instanceof Armor && Blacksmith.Quest.smithGlyph != null) {
							modifier = Blacksmith.Quest.smithGlyph.name();
						}
						sb.append("【").append(rewardItemName(item, modifier)).append("】");
					}
					GLog.w(sb.toString());
					break;
				}
			}
		}
	}

	private static boolean isAdjacentTo(Class<? extends Mob> npcClass) {
		for (Mob mob : Dungeon.level.mobs) {
			if (npcClass.isInstance(mob) && Dungeon.level.distance(Dungeon.hero.pos, mob.pos) == 1) return true;
		}
		return false;
	}

	private static String rewardItemName(Item item) { return rewardItemName(item, ""); }

	private static String rewardItemName(Item item, String modifier) {
		return String.format("%+d %s%s", item.level(), modifier, item.name());
	}
}
