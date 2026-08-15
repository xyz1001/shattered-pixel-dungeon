package com.shatteredpixel.shatteredpixeldungeon.mod;
import java.util.*;
public final class ModCatalog {
 private ModCatalog(){}
 public static List<ModOption> createOptions(){List<ModOption> options=new ArrayList<>();
  options.add(new com.shatteredpixel.shatteredpixeldungeon.mod.fullidentify.FullIdentify());
  options.add(new com.shatteredpixel.shatteredpixeldungeon.mod.starterequipment.StarterEquipment());
  options.add(new com.shatteredpixel.shatteredpixeldungeon.mod.secretradar.SecretRadar());
  options.add(new com.shatteredpixel.shatteredpixeldungeon.mod.doublegold.DoubleGold());
  options.add(new com.shatteredpixel.shatteredpixeldungeon.mod.starterbags.StarterBags());
  options.add(new com.shatteredpixel.shatteredpixeldungeon.mod.questpreview.QuestPreview());
  options.add(new com.shatteredpixel.shatteredpixeldungeon.mod.buffdurationstacking.BuffDurationStacking());
  options.add(new com.shatteredpixel.shatteredpixeldungeon.mod.shurikeninstantthrow.ShurikenInstantThrow());
  return options;
 }
}
