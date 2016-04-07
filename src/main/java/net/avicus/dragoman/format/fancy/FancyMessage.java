package net.avicus.dragoman.format.fancy;

import net.avicus.dragoman.Localizable;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.Locale;

public abstract class FancyMessage extends FancyAttributes implements Localizable {
    public abstract void addExtra(FancyMessage message);

    public abstract TextComponent toComponent(Locale locale);

    public final void addExtras(FancyMessage... messages) {
        for (FancyMessage message : messages)
            addExtra(message);
    }

    @Override
    public final String translate(Locale locale) {
        return toComponent(locale).toLegacyText();
    }
}
