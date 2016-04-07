package net.avicus.dragoman.format.fancy;

import net.avicus.dragoman.TranslationBundle;
import net.avicus.dragoman.format.Formatter;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FancyFormatter implements Formatter<FancyMessage, TextComponent> {
    private final TranslationBundle bundle;
    private final String key;

    public FancyFormatter(TranslationBundle bundle, String key) {
        this.bundle = bundle;
        this.key = key;
    }

    public FancyStyler format(FancyMessage... arguments) {
        return format(new ArrayList<>(Arrays.asList(arguments)));
    }

    @Override
    public FancyStyler format(List<FancyMessage> arguments) {
        return new FancyStyler(this.bundle, this.key, arguments);
    }

    public FancyStyler format(String arg1, String... arguments) {
        List<FancyMessage> fancy = new ArrayList<>();
        fancy.add(new UnlocalizedFancyMessage(arg1));
        for (String argument : arguments)
            fancy.add(new UnlocalizedFancyMessage(argument));
        return format(fancy);
    }

    public FancyStyler format(String arg1, ChatColor color1) {
        FancyStyler styler = format(arg1);
        styler.getArgument(0).color(color1);
        return styler;
    }

    public FancyStyler format(String arg1, ChatColor color1, String arg2, ChatColor color2) {
        FancyStyler styler = format(arg1, arg2);
        styler.getArgument(0).color(color1);
        styler.getArgument(1).color(color2);
        return styler;
    }
}
