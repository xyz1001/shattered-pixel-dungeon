package com.shatteredpixel.shatteredpixeldungeon.mod;
import com.watabou.utils.Bundle;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Combo;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MobSprite;
import java.util.ArrayList;
public interface ModOption {
 String id(); String title(); default boolean defaultEnabled(){return false;} default boolean isNewOnExistingSave(){return false;}
 default void onStart(){} default void onMove(){} default void onFloor(){} default void onNewGame(){}
 default int modifyGold(int quantity){return quantity;} default float modifyBerserkPowerLoss(float amount,float power,int hp,int ht){return amount;} default float modifyBerserkDamageFactor(float factor,float power){return factor;} default void onItemPickedUp(Item item){}
 default boolean isIdentified(Item item, boolean value){return value;}
 default boolean unstableSpellbookScrollAllowed(Scroll scroll){return false;}
 default ArrayList<Scroll> unstableSpellbookCandidates(ArrayList<Scroll> candidates){return candidates;}
 default Item recycleRunestone(Item original, Item result){return result;}
 default void onMobSpriteUpdate(MobSprite sprite, Mob mob){}
 default void onMobSpriteDestroy(MobSprite sprite){}
 default void onHeroReadyForInput(){}
 default void store(Bundle bundle){} default void restore(Bundle bundle){}
 default int modifyComboRequirement(int requirement, Combo.ComboMove move){return requirement;} default float modifyComboDamageMultiplier(float multiplier, Combo.ComboMove move, int count){return multiplier;}
}
