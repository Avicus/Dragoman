package net.avicus.dragoman.format;

import net.avicus.dragoman.Localizable;

import java.util.Locale;

public interface Styler<P, T> extends Localizable<T> {
    P getArgument(int num);

    T translate(Locale locale);
}
