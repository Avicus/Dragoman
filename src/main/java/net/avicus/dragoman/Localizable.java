package net.avicus.dragoman;

import java.util.Locale;

public interface Localizable<T> {
    T translate(Locale locale);
}
