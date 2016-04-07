package net.avicus.dragoman.format.simple;

import net.avicus.dragoman.Localizable;
import net.avicus.dragoman.TranslationBundle;
import net.avicus.dragoman.format.Formatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleFormatter implements Formatter<Localizable> {
    private final TranslationBundle bundle;
    private final String key;

    public SimpleFormatter(TranslationBundle bundle, String key) {
        this.bundle = bundle;
        this.key = key;
    }

    @SafeVarargs
    public final SimpleStyler format(Localizable... arguments) {
        return format(new ArrayList<>(Arrays.asList(arguments)));
    }

    @Override
    public final SimpleStyler format(List<Localizable> arguments) {
        return new SimpleStyler(this.bundle, this.key, arguments);
    }
}
