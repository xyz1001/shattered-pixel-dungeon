package com.shatteredpixel.shatteredpixeldungeon.mod.actiontimer;

import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MobSprite;
import com.watabou.noosa.BitmapText;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class ActionTimerOverlay extends BitmapText {

	private static final Map<MobSprite, ActionTimerOverlay> overlays = new HashMap<>();

	private ActionTimerOverlay() {
		super(PixelScene.pixelFont);
		hardlight(0xFFFF66);
		GameScene.effect(this);
	}

	public static void update(MobSprite sprite, Mob mob, Float remaining) {
		ActionTimerOverlay overlay = overlays.get(sprite);
		if (mob == null || remaining == null || !mob.isAlive() || !sprite.visible) {
			if (overlay != null) overlay.visible = false;
			return;
		}

		if (overlay == null) {
			overlay = new ActionTimerOverlay();
			overlays.put(sprite, overlay);
		}

		overlay.text(String.format(Locale.ROOT, "%.2f", remaining));
		overlay.measure();
		overlay.x = sprite.x + (sprite.width() - overlay.width()) / 2f;
		overlay.y = sprite.y - overlay.height() - 1;
		PixelScene.align(overlay);
		overlay.visible = true;
	}

	public static void remove(MobSprite sprite) {
		ActionTimerOverlay overlay = overlays.remove(sprite);
		if (overlay != null) overlay.killAndErase();
	}
}
