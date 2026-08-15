package com.shatteredpixel.shatteredpixeldungeon.mod.shurikeninstantthrow;
import com.shatteredpixel.shatteredpixeldungeon.mod.ModOption;
import com.shatteredpixel.shatteredpixeldungeon.mod.ModSettings;

public final class ShurikenInstantThrow implements ModOption {
 public String id(){return "shuriken-instant-throw";}
 public String title(){return "移动后手里剑即时投掷";}
 public boolean defaultEnabled(){return false;}
 public static boolean enabled(){return ModSettings.enabled("shuriken-instant-throw");}
}
