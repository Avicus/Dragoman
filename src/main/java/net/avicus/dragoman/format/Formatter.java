package net.avicus.dragoman.format;

import net.avicus.dragoman.Localizable;

import java.util.List;

public interface Formatter<P extends Localizable<R>, R> {
    Styler<P, R> format(List<P> values);
}
