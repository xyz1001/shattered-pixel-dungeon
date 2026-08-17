package com.shatteredpixel.shatteredpixeldungeon.mod.mysteriousguide;

import com.badlogic.gdx.Gdx;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/** Reads only this mod's dictionary bundle; the base game message registry stays untouched. */
final class DictText {
    private static final Properties TEXT = load();

    private DictText() {}

    static String get(String key) {
        String value = TEXT.getProperty("custom.dict.dict." + key);
        return value == null ? "m:dict." + key : value;
    }

    static String title(String key) {
        return Messages.titleCase(get(key));
    }

    private static Properties load() {
        Properties result = new Properties();
        String file = "custom_" + Messages.lang().code();
        try (InputStream stream = Gdx.files.internal("messages/custom/" + file + ".properties").read()) {
            result.load(new InputStreamReader(stream, StandardCharsets.UTF_8));
        } catch (Exception ignored) {
            try (InputStream stream = Gdx.files.internal("messages/custom/custom.properties").read()) {
                result.load(new InputStreamReader(stream, StandardCharsets.UTF_8));
            } catch (Exception ignoredAgain) {
                // Missing optional localization falls back to readable dictionary keys.
            }
        }
        return result;
    }
}
