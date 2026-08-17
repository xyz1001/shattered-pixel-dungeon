package com.shatteredpixel.shatteredpixeldungeon.mod.mysteriousguide;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;

/** Minimal item base kept local so this mod does not depend on another fork feature. */
abstract class LocalItem extends Item {
    {
        unique = true;
    }

    @Override
    public boolean isUpgradable() {
        return false;
    }

    @Override
    public boolean isIdentified() {
        return true;
    }
}
