package net.avicus.dragoman;

import java.util.*;

/**
 * A set of translations for a language (key associated to a value).
 */
public class TranslationSet {
    private final Locale locale;
    private final Map<String, String> set;

    public TranslationSet(Locale locale, Map<String, String> set) {
        this.locale = locale;
        this.set = set;
    }

    public TranslationSet(Locale locale) {
        this(locale, new HashMap<>());
    }

    public Locale getLocale() {
        return this.locale;
    }

    public Set<String> getKeys() {
        return this.set.keySet();
    }

    public Optional<String> get(String key) throws IllegalArgumentException {
        return Optional.ofNullable(this.set.get(key));
    }

    public void add(String key, String value) throws IllegalArgumentException {
        if (this.set.containsKey(key))
            throw new TranslationException("Key \"" + key + "\" already present in set.");
        this.set.put(key, value);
    }

    public boolean remove(String key) {
        return this.set.remove(key) != null;
    }
}
