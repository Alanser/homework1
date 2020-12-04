package ru.digitalhabbits.homework1.service;


import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import static java.util.Arrays.stream;

public class FileEngine {
    private static final String RESULT_FILE_PATTERN = "results-%s.txt";
    private static final String RESULT_DIR = "results";
    private static final String RESULT_EXT = "txt";
    private final String currentDir = System.getProperty("user.dir");

    public boolean writeToFile(@Nonnull String text, @Nonnull String pluginName) {
        boolean writed = false;
        String fileName = String.format(RESULT_FILE_PATTERN, pluginName);
        File resultDir = new File(RESULT_DIR);
        if (!resultDir.exists()) resultDir.mkdir();
        Path path = Paths.get(currentDir + "/" + RESULT_DIR + "/" + fileName);
        try {
            path.toFile().createNewFile();
            Path write = Files.write(path, Collections.singleton(text));
            writed = Files.exists(write);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writed;
    }

    public void cleanResultDir() {
        final File resultDir = new File(currentDir + "/" + RESULT_DIR);
        if (resultDir.exists() && resultDir.isDirectory()) {
            stream(resultDir.list((dir, name) -> name.endsWith(RESULT_EXT)))
                    .forEach(fileName -> new File(resultDir + "/" + fileName).delete());
        }
    }
}
