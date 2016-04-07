package net.avicus.dragoman;

import net.avicus.dragoman.format.fancy.FancyFormatter;
import net.avicus.dragoman.format.fancy.FancyStyler;
import net.avicus.dragoman.format.simple.SimpleFormatter;
import net.avicus.dragoman.format.simple.SimpleStyler;

import java.util.Locale;

public class Message {
    private static TranslationBundle bundle;

    static {
        TranslationSet en = new TranslationSet(new Locale("en"));
        en.add("hello.friend", "Hello, friend!");
        en.add("hello.unknown", "Hello, {0}!");
        en.add("names.alex", "Alexander");

        TranslationSet es = new TranslationSet(new Locale("es"));
        es.add("hello.friend", "Hola, amigo!");
        es.add("hello.unknown", "Hola, {0}!");
        es.add("names.alex", "Alejandro");

        bundle = new TranslationBundle(en, es);
    }

    public static final FancyFormatter FANCY_HELLO_FRIEND = new FancyFormatter(bundle, "hello.friend");
    public static final FancyFormatter FANCY_HELLO_UNKNOWN = new FancyFormatter(bundle, "hello.unknown");

    public static final SimpleFormatter HELLO_FRIEND = new SimpleFormatter(bundle, "hello.friend");
    public static final SimpleFormatter HELLO_UNKNOWN = new SimpleFormatter(bundle, "hello.unknown");

    public static final SimpleStyler NAME_ALEX = new SimpleFormatter(bundle, "names.alex").format();
    public static final FancyStyler FANCY_NAME_ALEX = new FancyFormatter(bundle, "names.alex").format();
}
