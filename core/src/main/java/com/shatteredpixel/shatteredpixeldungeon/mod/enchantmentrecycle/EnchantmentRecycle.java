package com.shatteredpixel.shatteredpixeldungeon.mod.enchantmentrecycle;

import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfEnchantment;
import com.shatteredpixel.shatteredpixeldungeon.mod.ModOption;
import com.watabou.utils.Random;

public final class EnchantmentRecycle implements ModOption {
    public String id(){return "enchantment-recycle";}
    public String title(){return "回收附魔符石";}
    public Item recycleRunestone(Item original, Item result){
        if (!(original instanceof Runestone)) return result;
        if (original instanceof StoneOfEnchantment) return result;
        return Random.Int(11) == 0 ? Generator.random(StoneOfEnchantment.class) : result;
    }
}
