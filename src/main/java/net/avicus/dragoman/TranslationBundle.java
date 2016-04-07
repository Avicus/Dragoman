package net.avicus.dragoman;

import java.util.*;

/**
 * A bundle of TranslationSet's.
 */
public class TranslationBundle {
    private final List<TranslationSet> sets;
    private Optional<TranslationSet> defaultSet;

    public TranslationBundle(List<TranslationSet> sets) {
        validate(sets);
        this.sets = sets;
        if (sets.size() > 0)
            this.defaultSet = Optional.of(sets.get(0));
        else
            this.defaultSet = Optional.empty();
    }

    public TranslationBundle(TranslationSet... sets) {
        this(new ArrayList<>(Arrays.asList(sets)));
    }

    public void add(TranslationSet set) {
        this.sets.add(set);
        validate(this.sets);
    }

    public boolean remove(TranslationSet set) {
        return this.sets.remove(set);
    }

    public List<TranslationSet> getSets() {
        return this.sets;
    }

    public Optional<TranslationSet> getSet(Locale locale) {
        for (TranslationSet set : this.sets)
            if (set.getLocale().equals(locale))
                return Optional.of(set);
        return Optional.empty();
    }

    public String translate(Locale locale, String key) {
        Optional<TranslationSet> set = getSet(locale);
        if (set.isPresent()) {
            Optional<String> result = set.get().get(key);
            if (result.isPresent())
                return result.get();

            if (this.defaultSet.isPresent()) {
                if (set.get().equals(this.defaultSet.get()))
                    throw new TranslationException("Default locale does not contain key \"" + key + "\".");
                return translate(this.defaultSet.get().getLocale(), key);
            }
        }
        else if (this.defaultSet.isPresent()) {
            return translate(this.defaultSet.get().getLocale(), key);
        }

        throw new TranslationException("Missing translation in locale \"" + locale + "\" for \"" + key + "\" (no default locale set).");
    }

    public Optional<Locale> getDefaultLocale() {
        return getDefaultSet().isPresent() ? Optional.of(getDefaultSet().get().getLocale()) : Optional.empty();
    }

    public Optional<TranslationSet> getDefaultSet() {
        return this.defaultSet;
    }

    public void setDefaultSet(TranslationSet set) {
        if (!this.sets.contains(set))
            add(set);
        this.defaultSet = Optional.of(set);
    }

    private static void validate(List<TranslationSet> list) {
        List<Locale> locales = new ArrayList<>();
        for (TranslationSet set : list) {
            if (locales.contains(set.getLocale()))
                throw new TranslationException("Translation bundle cannot contain sets of the same locale.");
            locales.add(set.getLocale());
        }
    }
}
