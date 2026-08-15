package com.shatteredpixel.shatteredpixeldungeon.mod.buffdurationstacking;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;

public final class HeroBuffDurationStacking {

	private HeroBuffDurationStacking() {
	}

	public static <T extends FlavourBuff> T affect(Char target, Class<T> buffClass, float duration) {
		if (BuffDurationStacking.enabled()) {
			return Buff.affect(target, buffClass, duration);
		} else {
			return Buff.prolong(target, buffClass, duration);
		}
	}
}
