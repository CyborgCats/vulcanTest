package com.vulcan.class_management.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class EnvLoader {
    private static final Map<String, String> ENV_VARS = new HashMap<>();

    static {
        try {
            Files.lines(Paths.get(".env"))
                    .filter(line -> line.contains("=") && !line.startsWith("#"))
                    .forEach(line -> {
                        String[] parts = line.split("=", 2);
                        ENV_VARS.put(parts[0].trim(), parts[1].trim());
                    });
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo el archivo .env", e);
        }
    }

    public static String get(String key) {
        return ENV_VARS.get(key);
    }
}
