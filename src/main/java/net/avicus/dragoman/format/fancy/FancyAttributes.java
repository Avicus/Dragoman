package net.avicus.dragoman.format.fancy;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

import java.util.Optional;

public class FancyAttributes implements FancyAttributable {
    private Optional<ChatColor> color = Optional.empty();
    private Optional<Boolean> bold = Optional.empty();
    private Optional<Boolean> italic = Optional.empty();
    private Optional<Boolean> underlined = Optional.empty();
    private Optional<Boolean> magic = Optional.empty();
    private Optional<Boolean> strike = Optional.empty();
    private Optional<ClickEvent> clickEvent = Optional.empty();
    private Optional<HoverEvent> hoverEvent = Optional.empty();

    public void inherit(FancyAttributes attributes) {
        this.color = this.color.isPresent() ? this.color : attributes.color;
        this.bold = this.bold.isPresent() ? this.bold : attributes.bold;
        this.italic = this.italic.isPresent() ? this.italic : attributes.italic;
        this.underlined = this.underlined.isPresent() ? this.underlined : attributes.underlined;
        this.magic = this.magic.isPresent() ? this.magic : attributes.magic;
        this.strike = this.strike.isPresent() ? this.strike : attributes.strike;
        this.clickEvent = this.clickEvent.isPresent() ? this.clickEvent : attributes.clickEvent;
        this.hoverEvent = this.hoverEvent.isPresent() ? this.hoverEvent : attributes.hoverEvent;
    }

    public FancyAttributable color(ChatColor color) {
        this.color = Optional.ofNullable(color);
        return this;
    }

    public FancyAttributable bold(Boolean bold) {
        this.bold = Optional.ofNullable(bold);
        return this;
    }

    public FancyAttributable italic(Boolean italic) {
        this.italic = Optional.ofNullable(italic);
        return this;
    }

    public FancyAttributable underlined(Boolean underlined) {
        this.underlined = Optional.ofNullable(underlined);
        return this;
    }

    public FancyAttributable magic(Boolean magic) {
        this.magic = Optional.ofNullable(magic);
        return this;
    }

    public FancyAttributable strike(Boolean strike) {
        this.strike = Optional.ofNullable(strike);
        return this;
    }

    public FancyAttributable onClick(ClickEvent event) {
        this.clickEvent = Optional.ofNullable(event);
        return this;
    }

    public FancyAttributable onHover(HoverEvent event) {
        this.hoverEvent = Optional.ofNullable(event);
        return this;
    }

    protected final TextComponent build(String text) {
        TextComponent message = new TextComponent(text);
        if (this.color.isPresent())
            message.setColor(net.md_5.bungee.api.ChatColor.valueOf(this.color.get().name()));
        if (this.bold.isPresent())
            message.setBold(this.bold.get());
        if (this.italic.isPresent())
            message.setItalic(this.italic.get());
        if (this.underlined.isPresent())
            message.setUnderlined(this.underlined.get());
        if (this.magic.isPresent())
            message.setObfuscated(this.magic.get());
        if (this.strike.isPresent())
            message.setStrikethrough(this.strike.get());
        if (this.clickEvent.isPresent())
            message.setClickEvent(this.clickEvent.get());
        if (this.hoverEvent.isPresent())
            message.setHoverEvent(this.hoverEvent.get());
        return message;
    }
}
