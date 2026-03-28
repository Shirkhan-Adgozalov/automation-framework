package com.shirkhan.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerHelper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String message) {
        System.out.println("[" + LocalDateTime.now().format(formatter) + "] " + message);
    }

    public static void info(String message) {
        log("INFO: " + message);
    }

    public static void error(String message) {
        log("ERROR: " + message);
    }
}