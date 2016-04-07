package net.avicus.dragoman.format.fancy;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.ChatColor;

interface FancyAttributable {
    void inherit(FancyAttributes attributes);

    FancyAttributable color(ChatColor color);

    FancyAttributable bold(Boolean bold);

    FancyAttributable italic(Boolean italic);

    FancyAttributable underlined(Boolean underlined);

    FancyAttributable magic(Boolean magic);

    FancyAttributable strike(Boolean strike);

    FancyAttributable onClick(ClickEvent event);

    FancyAttributable onHover(HoverEvent event);
}
