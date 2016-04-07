package net.avicus.dragoman.format.simple;

import net.avicus.dragoman.Localizable;
import net.avicus.dragoman.TranslationBundle;

import java.util.Locale;

public class LocalizedString implements Localizable {
    private final TranslationBundle bundle;
    private final String key;

    public LocalizedString(TranslationBundle bundle, String key) {
        this.bundle = bundle;
        this.key = key;
    }

    @Override
    public String translate(Locale locale) {
        return this.bundle.translate(locale, this.key);
    }
}
