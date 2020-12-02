package ru.digitalhabbits.homework1.plugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CounterPlugin
        implements PluginInterface {

    @Nullable
    @Override
    public String apply(@Nonnull String text) {
        int lines = text.split("\\n").length;
        int words = text.split("\\s+").length;
        long letters = text.chars().count();

        return lines + ";" + words + ";" + letters;
    }
}
