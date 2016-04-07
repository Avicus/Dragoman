package net.avicus.dragoman;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.*;
import java.util.*;

public class Translations {
    public static TranslationSet fromXml(File file) throws JDOMException, IOException {
        return fromXml(new FileInputStream(file));
    }

    public static TranslationSet fromXml(InputStream source) throws JDOMException, IOException {
        SAXBuilder sax = new SAXBuilder();
        Document document = sax.build(source);
        Element root = document.getRootElement();
        return fromXmlElement(root);
    }

    public static TranslationSet fromXmlElement(Element element) {
        if (!element.getName().equals("locale"))
            throw new TranslationException("XML element must be named <locale>.");

        String lang = element.getAttributeValue("lang");
        String country = element.getAttributeValue("country");

        if (lang == null)
            throw new TranslationException("Root XML element must have a lang attribute.");

        Locale locale = country == null ? new Locale(lang) : new Locale(lang, country);

        Map<String, String> set = new HashMap<>();

        for (Content content : element.getDescendants()) {
            if (content instanceof Element) {
                Element descendant = (Element) content;
                if (descendant.getChildren().size() == 0) {
                    String path = getPath(element, descendant);
                    set.put(path, descendant.getText());
                }
            }
        }

        return new TranslationSet(locale, set);
    }

    public static TranslationBundle fromFolder(File folder) throws IOException, JDOMException {
        File[] files = folder.listFiles();

        if (files == null)
            throw new IOException("Cannot read file/folder children of \"" + folder.getAbsolutePath()  + "\".");

        List<TranslationSet> sets = new ArrayList<>();

        for (File file : files) {
            if (file.getName().endsWith(".xml")) {
                TranslationSet set = fromXml(file);
                sets.add(set);
            }
        }

        return new TranslationBundle(sets);
    }

    private static String getPath(Element exclude, Element nested) {
        String result = nested.getName();
        Element curr = nested.getParentElement();
        while (!curr.equals(exclude)) {
            result = curr.getName() + "." + result;
            curr = curr.getParentElement();
        }
        return result;
    }

    public static TranslationSet mergeSets(List<TranslationSet> sets) throws IllegalArgumentException, TranslationException {
        if (sets.size() == 0)
            throw new IllegalArgumentException("At least one set must be provided to merge.");

        Locale locale = sets.get(0).getLocale();
        for (TranslationSet set : sets)
            if (!set.getLocale().equals(locale))
                throw new IllegalArgumentException("Cannot merge sets of different locales.");

        TranslationSet result = new TranslationSet(locale);

        for (TranslationSet merge : sets) {
            for (String key : merge.getKeys()) {
                try {
                    result.add(key, merge.get(key).get());
                } catch (Exception e) {
                    throw new TranslationException("Cannot merge translations that contain the same key.");
                }
            }
        }

        return result;
    }

    public static TranslationSet mergeSets(TranslationSet... sets) throws IllegalArgumentException, TranslationException {
        return mergeSets(Arrays.asList(sets));
    }

    public static TranslationBundle mergeBundles(List<TranslationBundle> bundles) throws IllegalArgumentException, TranslationException {
        if (bundles.size() == 0)
            throw new IllegalArgumentException("At least one bundle must be provided to merge.");

        Map<Locale, TranslationSet> sets = new HashMap<>();
        TranslationSet def = null;

        for (TranslationBundle merge : bundles) {
            for (TranslationSet set : merge.getSets()) {
                TranslationSet base;
                if (sets.containsKey(set.getLocale()))
                    base = sets.get(set.getLocale());
                else
                    base = new TranslationSet(set.getLocale());

                TranslationSet newSet = mergeSets(base, set);
                sets.put(set.getLocale(), newSet);

                if (merge.getDefaultSet().isPresent() && merge.getDefaultSet().get().equals(set)) {
                    if (def == null || def.getLocale().equals(set.getLocale())) {
                        def = newSet;
                    }
                }
            }
        }

        TranslationBundle result = new TranslationBundle();

        for (TranslationSet set : sets.values())
            result.add(set);

        if (def != null)
            result.setDefaultSet(def);

        return result;
    }

    public static TranslationBundle mergeBundles(TranslationBundle... bundles) throws IllegalArgumentException, TranslationException {
        return mergeBundles(Arrays.asList(bundles));
    }
}
