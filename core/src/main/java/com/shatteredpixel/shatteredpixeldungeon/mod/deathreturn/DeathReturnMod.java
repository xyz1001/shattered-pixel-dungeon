package com.shatteredpixel.shatteredpixeldungeon.mod.deathreturn;

import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Berserk;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.mod.ModOption;
import com.shatteredpixel.shatteredpixeldungeon.mod.ModSettings;
import com.shatteredpixel.shatteredpixeldungeon.scenes.TitleScene;
import com.watabou.noosa.Game;
import com.watabou.utils.Callback;

/** Conservatively abandons safe, ordinary lethal hero damage. */
public final class DeathReturnMod implements ModOption {
    public static final String ID = "death-return";
    private static volatile boolean returnQueued;

    @Override public String id() { return ID; }
    @Override public String title() { return "死亡返回主菜单"; }

    public static boolean intercept(Hero hero, int damage, Object cause) {
        if (!ModSettings.enabled(ID) || damage <= 0
                || !(cause instanceof com.shatteredpixel.shatteredpixeldungeon.actors.Char)) {
            return false;
        }

        // Deathless Fury can only become berserking after HP reaches zero.
        // Preserve all Berserk semantics by leaving every Berserk hero alone.
        if (hero.buff(Berserk.class) != null) return false;

        if (returnQueued) return true;
        returnQueued = true;

        Game.runOnRenderThread(new Callback() {
            @Override public void call() {
                try {
                    ShatteredPixelDungeon.switchNoFade(TitleScene.class);
                } finally {
                    returnQueued = false;
                }
            }
        });
        return true;
    }
}
