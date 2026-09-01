package dev.codewizz.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import dev.codewizz.input.console.Console;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void log(Object msg) {
        Console.printLine(prefix() + "[INFO]: " + msg.toString());
        Gdx.app.log(time(), prefix() + "[INFO]: " + msg.toString());
    }

    public static void warn(Object msg) {
        Console.printLine(prefix() + "[WARN]: " + msg.toString(), Color.ORANGE);
        Gdx.app.log(time(), prefix() + "[WARN]: " + msg.toString());
    }

    public static void error(Object msg) {
        Console.printLine(prefix() + "[ERROR]: " + msg.toString(), Color.RED);
        Gdx.app.error(time(), prefix() + "[ERROR]: " + msg.toString());
    }

    public static void error(Object msg, Throwable e) {
        Gdx.app.error(time(), prefix() + "[ERROR]: " + msg.toString(), e);
    }

    public static String time() {
        return TIME_FORMAT.format(LocalDateTime.now());
    }

    private static String prefix() {
        Thread t = Thread.currentThread();
        String s = t.getStackTrace()[3].getFileName().substring(0, t.getStackTrace()[3].getFileName().length()-5);
        return "[" + t.getName() + ":" + s + ":" + t.getStackTrace()[3].getLineNumber() + "] ";
    }
}
