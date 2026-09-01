package dev.codewizz.world;

import dev.codewizz.main.Registers;
import dev.codewizz.world.objects.*;

public class GameObjects {

    public static void register() {
        Registers.registerObject(Beacon.ID, Beacon.class);
        Registers.registerObject(Bush.ID, Bush.class);
        Registers.registerObject(Cow.ID, Cow.class);
        Registers.registerObject(FallenTree.ID, FallenTree.class);
        Registers.registerObject(Hermit.ID, Hermit.class);
        Registers.registerObject(Pebbles.ID, Pebbles.class);
        Registers.registerObject(SmallPile.ID, SmallPile.class);
        Registers.registerObject(Tree.ID, Tree.class);
        Registers.registerObject(TreeStump.ID, TreeStump.class);
    }
}
