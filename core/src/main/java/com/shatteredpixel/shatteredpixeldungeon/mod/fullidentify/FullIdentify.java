package com.shatteredpixel.shatteredpixeldungeon.mod.fullidentify;
import com.shatteredpixel.shatteredpixeldungeon.items.Item; import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion; import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll; import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring; import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand; import com.shatteredpixel.shatteredpixeldungeon.mod.*; import com.watabou.utils.Reflection;
public final class FullIdentify implements ModOption {
 public String id(){return "full-identify";} public String title(){return "默认全鉴定";}
 public void onStart(){for(Class<? extends Potion> c:Potion.getUnknown())try{Reflection.newInstance(c).identify();}catch(Exception ignored){} for(Class<? extends Scroll> c:Scroll.getUnknown())try{Reflection.newInstance(c).identify();}catch(Exception ignored){} for(Class<? extends Ring> c:Ring.getUnknown())try{Reflection.newInstance(c).setKnown();}catch(Exception ignored){}}
 public boolean isIdentified(Item i,boolean v){i.levelKnown=true;i.cursedKnown=true;if(i instanceof Wand)((Wand)i).curChargeKnown=true;return true;}
 public void onItemPickedUp(Item i){i.identify();}
}
