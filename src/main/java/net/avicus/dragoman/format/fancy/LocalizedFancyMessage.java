package net.avicus.dragoman.format.fancy;

import net.avicus.dragoman.Localizable;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocalizedFancyMessage extends FancyMessage {
    private final Localizable text;
    private final List<FancyMessage> extras;

    public LocalizedFancyMessage(Localizable text) {
        this.text = text;
        this.extras = new ArrayList<>();
    }

    public LocalizedFancyMessage(Localizable text, ChatColor color) {
        this(text);
        color(color);
    }

    @Override
    public void addExtra(FancyMessage extra) {
        this.extras.add(extra);
    }

    public TextComponent toComponent(Locale locale) {
        TextComponent text = build(this.text.translate(locale));
        for (FancyMessage extra : this.extras)
            text.addExtra(extra.translate(locale));
        return text;
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
