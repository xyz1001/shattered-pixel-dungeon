package com.shatteredpixel.shatteredpixeldungeon.mod;
import com.watabou.utils.Bundle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
public interface ModOption {
 String id(); String title(); default boolean defaultEnabled(){return false;} default boolean isNewOnExistingSave(){return false;}
 default void onStart(){} default void onMove(){} default void onFloor(){} default void onNewGame(){}
 default int modifyGold(int quantity){return quantity;} default void onItemPickedUp(Item item){}
 default boolean isIdentified(Item item, boolean value){return value;}
 default void store(Bundle bundle){} default void restore(Bundle bundle){}
}
