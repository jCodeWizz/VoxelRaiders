package dev.codewizz.main;

import dev.codewizz.gfx.gui.menus.ObjectMenu;
import dev.codewizz.input.console.CommandExecutor;
import dev.codewizz.utils.Logger;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.GameObjectInfo;
import dev.codewizz.world.GameObjectInfoShop;
import dev.codewizz.world.objects.IBuy;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Registers {

    public static final Map<String, CommandExecutor> commands = new HashMap<>();
    public static final Map<String, GameObjectInfo> objects = new HashMap<>();

    public static boolean registerCommand(String name, CommandExecutor e) {
        if (commands.containsKey(name)) {
            Logger.error("Tried to register new command: " + name + " but it already exists!");
            return false;
        }

        commands.put(name, e);
        return true;
    }

    public static boolean registerObject(GameObjectInfo info) {
        if (objects.containsKey(info.getId())) {
            Logger.error("Tried to register object " + info.getId() + " but it already exists!");
            return false;
        }

        objects.put(info.getId(), info);

        if (info instanceof GameObjectInfoShop) {
            ObjectMenu.objects.add((GameObjectInfoShop) info);
        }

        return true;
    }

    public static GameObject createObject(String id) {
        if (!objects.containsKey(id)) {
            Logger.error("Trying to create a GameObject for: " + id + " but it isn't registered!");
            return null;
        }

        try {
            return objects.get(id).getClazz().getConstructor().newInstance();
        } catch (Exception e) {
            Logger.error("Exception while trying to create GameObject: " + id, e);
            return null;
        }
    }
}
