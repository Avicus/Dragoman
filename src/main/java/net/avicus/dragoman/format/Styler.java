package net.avicus.dragoman.format;

import net.avicus.dragoman.Localizable;

import java.util.Locale;

public interface Styler<P> extends Localizable {
    P getArgument(int num);

    String translate(Locale locale);
}
