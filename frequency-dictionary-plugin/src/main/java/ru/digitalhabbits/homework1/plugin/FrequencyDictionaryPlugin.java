package ru.digitalhabbits.homework1.plugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FrequencyDictionaryPlugin
        implements PluginInterface {

    @Nullable
    @Override
    public String apply(@Nonnull String text) {
        Pattern pattern = Pattern.compile("(\\b[a-zA-Z][a-zA-Z.0-9]*\\b)");
        Matcher matcher = pattern.matcher(text);

        Map<String, Integer> wordFrequency = new TreeMap<>();
        Stream<MatchResult> results = matcher.results();
        results.forEach(mr -> wordFrequency.merge(mr.group().toLowerCase(), 1, Integer::sum));
        String result = wordFrequency.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + "\n")
                .reduce("", String::concat);

        return result;
    }
}
