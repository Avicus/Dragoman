package net.avicus.dragoman.format.simple;

import net.avicus.dragoman.Localizable;

import javax.annotation.Nullable;
import java.util.Locale;

public class UnlocalizedString implements Localizable {
    private final String text;

    public UnlocalizedString(String text) {
        this.text = text;
    }

    @Override
    public String translate(@Nullable Locale locale) {
        return this.text;
    }
}
