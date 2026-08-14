package com.shatteredpixel.shatteredpixeldungeon.mod;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import java.util.HashSet; import java.util.Set;
public final class ModSettings {
 private static final String PREFIX="mod.default."; private static final Set<String> active=new HashSet<>(); private static boolean confirmed;
 private ModSettings(){}
 public static boolean defaultEnabled(String id){return SPDSettings.getBoolean(PREFIX+id,false);}
 private static String marker(String id){return PREFIX+"set."+id;}
 public static boolean hasDefault(String id){return SPDSettings.getBoolean(marker(id),false);}
 public static void setDefault(String id, boolean value){SPDSettings.put(PREFIX+id,value);SPDSettings.put(marker(id),true);}
 public static boolean legacy(String id){String key; if("full-identify".equals(id))key="mod_auto_identify"; else if("starter-equipment".equals(id))key="mod_starter_equip"; else if("secret-radar".equals(id))key="mod_secret_radar"; else if("double-gold".equals(id))key="mod_double_gold"; else if("starter-bags".equals(id))key="mod_starter_bags"; else key="mod_quest_preview"; return SPDSettings.getBoolean(key,"quest-preview".equals(id));}
 static void active(Set<String> ids){active.clear(); active.addAll(ids);} static Set<String> active(){return new HashSet<>(active);}
 static void confirmed(boolean value){confirmed=value;} static boolean confirmed(){return confirmed;}
 public static boolean enabled(String id){return active.contains(id);}
}
