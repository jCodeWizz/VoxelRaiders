package dev.codewizz.world;

import dev.codewizz.main.Registers;
import dev.codewizz.world.objects.*;

public class GameObjects {

    public static void register() {
        Registers.registerObject(Well.INFO);
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
