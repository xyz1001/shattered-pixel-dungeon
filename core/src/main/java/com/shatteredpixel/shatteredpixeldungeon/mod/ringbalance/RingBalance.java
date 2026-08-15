package com.shatteredpixel.shatteredpixeldungeon.mod.ringbalance;

import com.shatteredpixel.shatteredpixeldungeon.mod.ModOption;
import com.shatteredpixel.shatteredpixeldungeon.mod.ModSettings;

public final class RingBalance implements ModOption {
	public String id(){return "ring-balance";}
	public String title(){return "戒指数值平衡";}

	public static boolean enabled(){
		return ModSettings.enabled("ring-balance");
	}
}
