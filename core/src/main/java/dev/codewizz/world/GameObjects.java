package dev.codewizz.world;

import dev.codewizz.gfx.gui.menus.ObjectMenu;
import dev.codewizz.main.Registers;
import dev.codewizz.world.inventory.Item;
import dev.codewizz.world.inventory.types.ItemType;
import dev.codewizz.world.objects.*;

public class GameObjects {

    public static void register() {
        Registers.registerObject(Beacon.INFO);
        Registers.registerObject(Bush.INFO);
        Registers.registerObject(Cow.INFO);
        Registers.registerObject(FallenTree.INFO);
        Registers.registerObject(Hermit.INFO);
        Registers.registerObject(Pebbles.INFO);
        Registers.registerObject(Placeholder.INFO);
        Registers.registerObject(SmallPile.INFO);
        Registers.registerObject(Tree.INFO);
        Registers.registerObject(TreeStump.INFO);
    }
}
