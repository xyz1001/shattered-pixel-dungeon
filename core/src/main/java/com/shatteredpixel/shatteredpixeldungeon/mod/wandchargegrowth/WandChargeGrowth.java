package com.shatteredpixel.shatteredpixeldungeon.mod.wandchargegrowth;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MagesStaff;
import com.shatteredpixel.shatteredpixeldungeon.mod.ModOption;

public final class WandChargeGrowth implements ModOption {
	public String id(){return "wand_charge_growth";}
	public String title(){return "法杖充能成长";}

	public void onStart(){
		if (Dungeon.hero == null) return;
		for (Wand wand : Dungeon.hero.belongings.getAllItems(Wand.class)) wand.updateLevel();
		MagesStaff staff = Dungeon.hero.belongings.getItem(MagesStaff.class);
		if (staff != null) staff.updateWand(false);
	}

	public void onItemPickedUp(Item item){
		if (item instanceof Wand) ((Wand) item).updateLevel();
		else if (item instanceof MagesStaff) ((MagesStaff) item).updateWand(false);
	}

	public int modifyMaxCharges(int calculatedMaximum, int vanillaMaximum){
		return calculatedMaximum;
	}
}
