package net.avicus.dragoman.format;

import net.avicus.dragoman.Localizable;

import java.util.List;

public interface Formatter<P extends Localizable> {
    Styler<P> format(List<P> values);
}
