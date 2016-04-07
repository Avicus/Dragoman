package net.avicus.dragoman.format.fancy;

import net.avicus.dragoman.Localizable;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

import java.util.Locale;

public class LocalizedFancyMessage extends FancyMessage {
    private final Localizable<String> text;

    public LocalizedFancyMessage(Localizable<String> text) {
        this.text = text;
    }

    @Override
    public TextComponent translate(Locale locale) {
        return build(this.text.translate(locale));
    }

    @Override
    public LocalizedFancyMessage color(ChatColor color) {
        super.color(color);
        return this;
    }

    @Override
    public LocalizedFancyMessage bold(Boolean bold) {
        super.bold(bold);
        return this;
    }

    @Override
    public LocalizedFancyMessage italic(Boolean italic) {
        super.italic(italic);
        return this;
    }

    @Override
    public LocalizedFancyMessage underlined(Boolean underlined) {
        super.underlined(underlined);
        return this;
    }

    @Override
    public LocalizedFancyMessage magic(Boolean magic) {
        super.magic(magic);
        return this;
    }

    @Override
    public LocalizedFancyMessage strike(Boolean strike) {
        super.strike(strike);
        return this;
    }

    @Override
    public LocalizedFancyMessage onClick(ClickEvent event) {
        super.onClick(event);
        return this;
    }

    @Override
    public LocalizedFancyMessage onHover(HoverEvent event) {
        super.onHover(event);
        return this;
    }
}
