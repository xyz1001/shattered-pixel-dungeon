package com.shatteredpixel.shatteredpixeldungeon.mod.starterequipment;
import com.shatteredpixel.shatteredpixeldungeon.mod.ModOption;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
public final class StarterEquipment implements ModOption {
 public String id(){return "starter-equipment";} public String title(){return "开局自选装备";}
 public void onStart(){GameScene.show(new WndStarterEquipCategory());}
}
