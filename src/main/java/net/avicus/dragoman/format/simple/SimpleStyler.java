package net.avicus.dragoman.format.simple;

import net.avicus.dragoman.Localizable;
import net.avicus.dragoman.TranslationBundle;
import net.avicus.dragoman.format.Styler;

import java.util.List;
import java.util.Locale;

public class SimpleStyler implements Styler<Localizable<String>, String> {
    private final TranslationBundle bundle;
    private final String key;
    private final List<Localizable<String>> arguments;

    public SimpleStyler(TranslationBundle bundle, String key, List<Localizable<String>> arguments) {
        this.bundle = bundle;
        this.key = key;
        this.arguments = arguments;
    }

    @Override
    public Localizable<String> getArgument(int num) {
        return this.arguments.get(num);
    }

    @Override
    public String translate(Locale locale) {
        String translated = this.bundle.translate(locale, key);

        for (int i = 0; i < this.arguments.size(); i++)
            translated = translated.replace("{" + i + "}", this.arguments.get(i).translate(locale));

        return translated;
    }
}
