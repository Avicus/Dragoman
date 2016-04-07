package net.avicus.dragoman.format.fancy;

import net.avicus.dragoman.TranslationBundle;
import net.avicus.dragoman.format.Formatter;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FancyFormatter implements Formatter<FancyMessage, TextComponent> {
    private final TranslationBundle bundle;
    private final String key;

    public FancyFormatter(TranslationBundle bundle, String key) {
        this.bundle = bundle;
        this.key = key;
    }

    public FancyStyler format(FancyMessage... arguments) {
        return format(new ArrayList<>(Arrays.asList(arguments)));
    }

    @Override
    public FancyStyler format(List<FancyMessage> arguments) {
        return new FancyStyler(this.bundle, this.key, arguments);
    }
}
