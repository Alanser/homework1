package ru.digitalhabbits.homework1.service;

import ru.digitalhabbits.homework1.plugin.PluginInterface;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;

public class PluginEngine {

    @Nonnull
    public <T extends PluginInterface> String applyPlugin(@Nonnull Class<T> cls, @Nonnull String text) {
        String result = "";
        try {
            T plugin = cls.getDeclaredConstructor().newInstance();
            Method apply = PluginInterface.class.getDeclaredMethod("apply", String.class);
            result = (String) apply.invoke(plugin, text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
