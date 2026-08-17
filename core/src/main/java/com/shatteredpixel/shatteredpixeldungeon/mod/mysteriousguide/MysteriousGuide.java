package com.shatteredpixel.shatteredpixeldungeon.mod.mysteriousguide;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.mod.ModOption;

public class MysteriousGuide implements ModOption {
	@Override
	public String id() {
		return "mysterious-guide";
	}

	@Override
	public String title() {
		return "神秘指南";
	}

	@Override
	public void onStart() {
		if (Dungeon.hero == null) return;

		if (Dungeon.hero.belongings.getItem(DictBook.class) == null) {
			new DictBook().collect(Dungeon.hero.belongings.backpack);
		}
	}
}
