package net.avicus.dragoman.format.fancy;

import net.avicus.dragoman.TranslationBundle;
import net.avicus.dragoman.format.Styler;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FancyStyler extends FancyMessage implements Styler<FancyMessage>, FancyAttributable {
    private final TranslationBundle bundle;
    private final String key;
    private final List<FancyMessage> arguments;
    private final FancyAttributes attributes;
    private final List<FancyMessage> extras;

    public FancyStyler(TranslationBundle bundle, String key, List<FancyMessage> arguments) {
        this.bundle = bundle;
        this.key = key;
        this.arguments = arguments;
        this.attributes = new FancyAttributes();
        this.extras = new ArrayList<>();
    }

    @Override
    public FancyMessage getArgument(int num) {
        return this.arguments.get(num);
    }

    public TextComponent toComponent(Locale locale) {
        String raw = this.bundle.translate(locale, this.key);

        List<TextComponent> parts = new ArrayList<>();

        for (int i = 0; i < this.arguments.size(); i++) {
            if (raw.contains("{" + i + "}")) {
                String[] split = raw.split("\\{" + i + "\\}");

                FancyMessage before = new UnlocalizedFancyMessage(split[0]);
                before.inherit(this.attributes);

                FancyMessage arg = this.arguments.get(i);
                arg.inherit(this.attributes);

                parts.add(before.toComponent(locale));
                parts.add(arg.toComponent(locale));
                if (split.length > 1)
                    raw = split[1];
                else
                    raw = "";
            }
            else {
                break;
            }
        }

        if (raw.length() > 0) {
            FancyMessage remaining = new UnlocalizedFancyMessage(raw);
            remaining.inherit(this.attributes);
            parts.add(remaining.toComponent(locale));
        }

        if (parts.size() == 0)
            return new TextComponent("");

        TextComponent result = new TextComponent(parts.get(0));
        for (int i = 1; i < parts.size(); i++)
            result.addExtra(parts.get(i));

        for (FancyMessage extra : this.extras) {
            extra.inherit(this.attributes);
            result.addExtra(extra.toComponent(locale));
        }

        return result;
    }

    @Override
    public void addExtra(FancyMessage message) {
        this.extras.add(message);
    }

    @Override
    public void inherit(FancyAttributes attributes) {
        this.attributes.inherit(attributes);
    }

    @Override
    public FancyStyler color(ChatColor color) {
        this.attributes.color(color);
        return this;
    }

    @Override
    public FancyStyler bold(Boolean bold) {
        this.attributes.bold(bold);
        return this;
    }

    @Override
    public FancyStyler italic(Boolean italic) {
        this.attributes.italic(italic);
        return this;
    }

    @Override
    public FancyStyler underlined(Boolean underlined) {
        this.attributes.underlined(underlined);
        return this;
    }

    @Override
    public FancyStyler magic(Boolean magic) {
        this.attributes.magic(magic);
        return this;
    }

    @Override
    public FancyStyler strike(Boolean strike) {
        this.attributes.strike(strike);
        return this;
    }

    @Override
    public FancyStyler onClick(ClickEvent event) {
        this.attributes.onClick(event);
        return this;
    }

    @Override
    public FancyStyler onHover(HoverEvent event) {
        this.attributes.onHover(event);
        return this;
    }
}
