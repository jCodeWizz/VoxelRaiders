package dev.codewizz.main;

import dev.codewizz.input.console.CommandExecutor;
import dev.codewizz.utils.Logger;

import java.util.HashMap;

public class Registers {

    public static final HashMap<String, CommandExecutor> commands = new HashMap<>();

    public static boolean registerCommand(String name, CommandExecutor e) {
        if(commands.containsKey(name)) {
            Logger.error("Tried to register new command: " + name + " but it already exists!");
            return false;
        }

        commands.put(name, e);
        return true;
    }
}
