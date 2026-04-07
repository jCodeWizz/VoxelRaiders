package dev.codewizz.world.objects;

import dev.codewizz.gfx.Renderer;
import dev.codewizz.world.GameObject;

public abstract class Gatherable extends GameObject {



    public Gatherable(String id) {
        super(id);
    }

    public abstract void gather();

    @Override
    public void update(float dt) {
    }
}
