package org.example.utils;

import java.util.Map;
import java.util.Set;

public class Constants {
    public static final Set<String> ALLOWED_PATHS = Set.of("", "/ServletCRUD/auth", "/ServletCRUD/images/.*$", "/ServletCRUD/uploads/.*$");
    public static final Map<String, String> env = System.getenv();
    public static final Set<String> ALLOWED_ORIGINS = Set.of("http://localhost:4200", "http://localhost:8080");
}
