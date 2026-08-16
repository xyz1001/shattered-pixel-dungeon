package com.shatteredpixel.shatteredpixeldungeon.mod.warriorbalance;

import com.shatteredpixel.shatteredpixeldungeon.mod.ModOption;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Combo;
import com.watabou.utils.GameMath;

public final class WarriorBalance implements ModOption {
	public String id(){return "warrior-optimization";}
	public String title(){return "战士优化";}
	public boolean isNewOnExistingSave(){return true;}

	public float modifyBerserkPowerLoss(float amount, float power, int hp, int ht){
		return GameMath.gate(0.1f, power, 1f) * 0.005f * (float)Math.pow(hp / (float)ht, 2.321928f);
	}

	public float modifyBerserkDamageFactor(float factor, float power){
		return Math.min(2f, 1f + power);
	}

	public int modifyComboRequirement(int requirement, Combo.ComboMove move){
		return requirement / 2;
	}

	public float modifyComboDamageMultiplier(float multiplier, Combo.ComboMove move, int count){
		if (move == Combo.ComboMove.CRUSH) return 0.5f * count;
		if (move == Combo.ComboMove.FURY) return 1f;
		return multiplier;
	}
}
