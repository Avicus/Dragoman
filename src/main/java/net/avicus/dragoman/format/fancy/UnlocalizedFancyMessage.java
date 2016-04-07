package net.avicus.dragoman.format.fancy;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UnlocalizedFancyMessage extends FancyMessage {
    private final String text;
    private final List<FancyMessage> extras;

    public UnlocalizedFancyMessage(String text) {
        this.text = text;
        this.extras = new ArrayList<>();
    }

    public UnlocalizedFancyMessage(String text, ChatColor color) {
        this(text);
        color(color);
    }

    public void addExtra(FancyMessage message) {
        this.extras.add(message);
    }

    @Override
    public TextComponent toComponent(@Nullable Locale locale) {
        TextComponent component = super.build(this.text);
        for (FancyMessage extra : this.extras)
            component.addExtra(extra.translate(locale));
        return component;
    }

    @Override
    public UnlocalizedFancyMessage color(ChatColor color) {
        super.color(color);
        return this;
    }

    @Override
    public UnlocalizedFancyMessage bold(Boolean bold) {
        super.bold(bold);
        return this;
    }

    @Override
    public UnlocalizedFancyMessage italic(Boolean italic) {
        super.italic(italic);
        return this;
    }

    @Override
    public UnlocalizedFancyMessage underlined(Boolean underlined) {
        super.underlined(underlined);
        return this;
    }

    @Override
    public UnlocalizedFancyMessage magic(Boolean magic) {
        super.magic(magic);
        return this;
    }

    @Override
    public UnlocalizedFancyMessage strike(Boolean strike) {
        super.strike(strike);
        return this;
    }

    @Override
    public UnlocalizedFancyMessage onClick(ClickEvent event) {
        super.onClick(event);
        return this;
    }

    @Override
    public UnlocalizedFancyMessage onHover(HoverEvent event) {
        super.onHover(event);
        return this;
    }
}
