package com.shatteredpixel.shatteredpixeldungeon.mod.buffdurationstacking;
import com.shatteredpixel.shatteredpixeldungeon.mod.ModOption;
import com.shatteredpixel.shatteredpixeldungeon.mod.ModSettings;

public final class BuffDurationStacking implements ModOption {
 public String id(){return "buff-duration-stacking";}
 public String title(){return "buff时间叠加";}
 public boolean defaultEnabled(){return false;}
 public static boolean enabled(){return ModSettings.enabled("buff-duration-stacking");}
}
