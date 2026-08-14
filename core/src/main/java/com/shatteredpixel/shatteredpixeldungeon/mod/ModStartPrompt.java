package com.shatteredpixel.shatteredpixeldungeon.mod;

import com.badlogic.gdx.Gdx;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;

public final class ModStartPrompt {
 private static Hero shown;
 private ModStartPrompt(){}

 public static void check(){
  if(ModRegistry.options().isEmpty()||ModSettings.confirmed()||Dungeon.depth!=1||Dungeon.hero==null||shown==Dungeon.hero)return;
  shown=Dungeon.hero;
  Gdx.app.postRunnable(()->GameScene.show(new WndModSelect()));
 }

 public static void checkPending(){
  if (ModRegistry.hasPending() && Dungeon.hero != null){
   GameScene.show(new WndModSelect(true));
  }
 }
}
