package com.shatteredpixel.shatteredpixeldungeon.mod.starterbags;

import com.shatteredpixel.shatteredpixeldungeon.mod.ModOption;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.PotionBandolier;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.ScrollHolder;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.VelvetPouch;

public class StarterBags implements ModOption {
	public String id(){return "starter-bags";} public String title(){return "开局四大收纳袋";} public void onStart(){giveStarterBags();}

	public void giveStarterBags() {
		if (Dungeon.hero == null) return;

		Bag[] starterBags = new Bag[]{
				new PotionBandolier(),
				new ScrollHolder(),
				new VelvetPouch(),
				new MagicalHolster()
		};

		for (Bag bag : starterBags) {
			if (Dungeon.hero.belongings.getItem(bag.getClass()) == null) {
				bag.collect(Dungeon.hero.belongings.backpack);
			}
		}

		// 同步置位原版全局掉落标记，通知原版商店不再重复生成出售
		Dungeon.LimitedDrops.POTION_BANDOLIER.drop();
		Dungeon.LimitedDrops.SCROLL_HOLDER.drop();
		Dungeon.LimitedDrops.VELVET_POUCH.drop();
		Dungeon.LimitedDrops.MAGICAL_HOLSTER.drop();
	}
}
