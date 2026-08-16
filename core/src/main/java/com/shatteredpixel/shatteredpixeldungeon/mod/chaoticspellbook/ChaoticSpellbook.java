package com.shatteredpixel.shatteredpixeldungeon.mod.chaoticspellbook;

import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTransmutation;
import com.shatteredpixel.shatteredpixeldungeon.mod.ModOption;
import com.watabou.utils.Random;
import java.util.ArrayList;

public final class ChaoticSpellbook implements ModOption {
    public String id(){return "chaotic-spellbook";}
    public String title(){return "无序魔典优化";}
    public boolean unstableSpellbookScrollAllowed(Scroll scroll){
        return scroll instanceof ScrollOfTransmutation && Random.Int(2) != 0;
    }
    public ArrayList<Scroll> unstableSpellbookCandidates(ArrayList<Scroll> candidates){
        ArrayList<Scroll> result = new ArrayList<>(candidates);
        while (result.size() < 5){
            Scroll scroll = (Scroll) Generator.randomUsingDefaults(Generator.Category.SCROLL);
            if (scroll == null || ((scroll instanceof ScrollOfIdentify || scroll instanceof ScrollOfRemoveCurse
                    || scroll instanceof ScrollOfMagicMapping || scroll instanceof ScrollOfTransmutation)
                    && Random.Int(2) == 0) || contains(result, scroll.getClass())) continue;
            result.add(scroll);
        }
        return result;
    }
    private boolean contains(ArrayList<Scroll> scrolls, Class<?> type){
        for (Scroll scroll : scrolls) if (scroll.getClass().equals(type)) return true;
        return false;
    }
}
