package com.shatteredpixel.shatteredpixeldungeon.mod.doublegold;

import com.shatteredpixel.shatteredpixeldungeon.mod.ModOption;

public class DoubleGold implements ModOption {
	public String id(){return "double-gold";} public String title(){return "三倍金币掉落";} public int modifyGold(int n){return n*3;}
}
