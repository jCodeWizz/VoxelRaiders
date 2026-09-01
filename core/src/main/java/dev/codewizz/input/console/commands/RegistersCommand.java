package dev.codewizz.input.console.commands;

import dev.codewizz.input.console.CommandExecutor;
import dev.codewizz.input.console.Console;
import dev.codewizz.main.Registers;
import dev.codewizz.world.World;

public class RegistersCommand implements CommandExecutor {

    @Override
    public String getUsage() {
        return "registers [list] [register_name]";
    }

    @Override
    public boolean execute(String command, World world, String[] args) {
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("list")) {
                if (args[1].equalsIgnoreCase("objects")) {
                    Console.printLine("Object list: ");
                    for (String value : Registers.objects.keySet()) {
                        Console.printLine(value);
                    }
                    return true;
                } else if (args[1].equalsIgnoreCase("commands")) {
                    Console.printLine("Command list: ");
                    for (String value : Registers.commands.keySet()) {
                        Console.printLine(value);
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
