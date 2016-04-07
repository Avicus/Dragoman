package net.avicus.dragoman.format.fancy;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

import javax.annotation.Nullable;
import java.util.Locale;

public class UnlocalizedFancyMessage extends FancyMessage {
    private final String text;

    public UnlocalizedFancyMessage(String text) {
        this.text = text;
    }

    @Override
    public TextComponent translate(@Nullable Locale locale) {
        return super.build(this.text);
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
