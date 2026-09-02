package dev.codewizz.world;

import dev.codewizz.gfx.gui.menus.ObjectMenu;
import dev.codewizz.main.Registers;
import dev.codewizz.world.inventory.Item;
import dev.codewizz.world.inventory.types.ItemType;
import dev.codewizz.world.objects.*;

public class GameObjects {

    public static void register() {
        Registers.registerObject(Beacon.ID, Beacon.class);
        Registers.registerObject(Bush.ID, Bush.class);
        Registers.registerObject(Cow.ID, Cow.class);
        Registers.registerObject(FallenTree.ID, FallenTree.class);
        Registers.registerObject(Hermit.ID, Hermit.class);
        Registers.registerObject(Pebbles.ID, Pebbles.class);
        Registers.registerObject(SmallPile.ID, SmallPile.class, new ObjectMenu.Info(SmallPile.ID, SmallPile.ID, "Small Pile", "Storage for 1", new Item(ItemType.LOG, 2), new Item(ItemType.PLANK, 3)));
        Registers.registerObject(Tree.ID, Tree.class);
        Registers.registerObject(TreeStump.ID, TreeStump.class);
    }
}
