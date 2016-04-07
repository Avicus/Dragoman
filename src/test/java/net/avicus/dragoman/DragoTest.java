package net.avicus.dragoman;

import net.avicus.dragoman.format.fancy.FancyFormatter;
import net.avicus.dragoman.format.fancy.FancyStyler;
import net.avicus.dragoman.format.fancy.UnlocalizedFancyMessage;
import net.avicus.dragoman.format.simple.SimpleFormatter;
import net.avicus.dragoman.format.simple.SimpleStyler;
import net.avicus.dragoman.format.simple.UnlocalizedString;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.jdom2.JDOMException;
import org.junit.Test;

import java.io.IOException;
import java.util.Locale;

public class DragoTest {
    @Test
    public void merge() {
        TranslationSet en1 = new TranslationSet(new Locale("en"));
        en1.add("hello", "Hello, friend!");
        en1.add("bye", "Bu-bye, evil man.");

        TranslationSet es1 = new TranslationSet(new Locale("es"));
        es1.add("hello", "Hola, amigo!");
        es1.add("bye", "Adios, hombre malo.");

        TranslationBundle bundle1 = new TranslationBundle(en1, es1);

        TranslationSet en2 = new TranslationSet(new Locale("en"));
        en2.add("where", "Where are you?!");

        TranslationBundle bundle2 = new TranslationBundle(en2);

        TranslationBundle merged = Translations.mergeBundles(bundle1, bundle2);

        System.out.println(merged.getDefaultSet().get().getKeys());

        System.out.println(merged.translate(new Locale("en"), "where"));
    }

    @Test
    public void bundle() {
        TranslationSet en = new TranslationSet(new Locale("en"));
        en.add("hello", "Hello, friend!");
        en.add("bye", "Bu-bye, evil man.");

        TranslationSet es = new TranslationSet(new Locale("es"));
        es.add("hello", "Hola, amigo!");
        es.add("bye", "Adios, hombre malo.");

        TranslationBundle bundle = new TranslationBundle(en, es);

        System.out.println(bundle.translate(new Locale("es"), "hello"));
    }

    @Test
    public void fromXml() throws JDOMException, IOException {
        TranslationSet set = Translations.fromXml(this.getClass().getClassLoader().getResourceAsStream("./test.xml"));

        System.out.println(set.get("what.a.mess"));
    }

    @Test
    public void simpleFormatter() {
        TranslationSet en = new TranslationSet(new Locale("en"));
        en.add("hello", "Hello, {0}!");

        TranslationBundle bundle = new TranslationBundle(en);

        SimpleFormatter formatter = new SimpleFormatter(bundle, "hello");
        SimpleStyler styler = formatter.format(new UnlocalizedString("Bill"));

        String result = styler.translate(new Locale("en"));

        System.out.println(result);
    }

    @Test
    public void fancyFormatter() {
        TranslationSet en = new TranslationSet(new Locale("en"));
        en.add("hello", "Hello, {0}!");

        TranslationBundle bundle = new TranslationBundle(en);

        FancyFormatter formatter = new FancyFormatter(bundle, "hello");
        FancyStyler styler = formatter.format(new UnlocalizedFancyMessage("Bob").bold(true));

        TextComponent result = styler.translate(new Locale("en"));

        System.out.println(result.toLegacyText());
    }

    @Test
    public void staticTest() {
        Locale en = new Locale("en");
        Locale es = new Locale("es");

        FancyStyler test1 = Message.FANCY_HELLO_FRIEND.format();
        FancyStyler test2 = Message.FANCY_HELLO_UNKNOWN.format(Message.FANCY_NAME_ALEX.bold(true).italic(true)).color(ChatColor.GOLD);


        System.out.println(test1.translate(en).toLegacyText());
        System.out.println(test1.translate(es).toLegacyText());

        System.out.println(test2.translate(en).toLegacyText());
        System.out.println(test2.translate(es).toLegacyText());
    }
}
