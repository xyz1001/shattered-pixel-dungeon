package com.shatteredpixel.shatteredpixeldungeon.mod.actiontimer;

import com.shatteredpixel.shatteredpixeldungeon.mod.ModOption;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MobSprite;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;

public final class ActionTimer implements ModOption {

	private volatile Map<Mob, Float> cooldowns = Collections.emptyMap();

	@Override
	public String id() {
		return "enemy-action-countdown";
	}

	@Override
	public String title() {
		return "敌人行动倒计时";
	}

	@Override
	public void onHeroReadyForInput() {
		IdentityHashMap<Mob, Float> snapshot = new IdentityHashMap<>();
		if (Dungeon.level != null) {
			for (Mob mob : Dungeon.level.mobs) {
				snapshot.put(mob, Math.max(0f, mob.cooldown()));
			}
		}
		cooldowns = Collections.unmodifiableMap(snapshot);
	}

	@Override
	public void onMobSpriteUpdate(MobSprite sprite, Mob mob) {
		ActionTimerOverlay.update(sprite, mob, mob == null ? null : cooldowns.get(mob));
	}

	@Override
	public void onMobSpriteDestroy(MobSprite sprite) {
		ActionTimerOverlay.remove(sprite);
	}
}
