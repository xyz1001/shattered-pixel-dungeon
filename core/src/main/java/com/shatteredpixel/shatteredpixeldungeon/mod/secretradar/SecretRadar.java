package com.shatteredpixel.shatteredpixeldungeon.mod.secretradar;

import com.shatteredpixel.shatteredpixeldungeon.mod.ModOption;
import com.shatteredpixel.shatteredpixeldungeon.mod.ModRegistry;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

import java.util.HashSet;

public class SecretRadar implements ModOption {
	public String id(){return "secret-radar";} public String title(){return "隐藏房间感知雷达";} public void onMove(){update();} public void onFloor(){reset();} public void onNewGame(){reset();}

	private static final HashSet<Integer> inRangeSecretCells = new HashSet<>();

	public static void reset() { inRangeSecretCells.clear(); }

	public static void update() {
		int heroPos = Dungeon.hero.pos;
		int radius = 3;
		int len = Dungeon.level.length();

		HashSet<Integer> newInRange = new HashSet<>();
		boolean newlyEntered = false;

		for (int cell = 0; cell < len; cell++) {
			if (Dungeon.level.map[cell] == Terrain.SECRET_DOOR && Dungeon.level.distance(heroPos, cell) <= radius) {
				newInRange.add(cell);
				if (!inRangeSecretCells.contains(cell)) newlyEntered = true;
			}
		}

		if (newlyEntered) GLog.w("（雷达感应）你察觉到附近 3 格内存在隐藏墙壁！");
		inRangeSecretCells.clear();
		inRangeSecretCells.addAll(newInRange);
	}
}
