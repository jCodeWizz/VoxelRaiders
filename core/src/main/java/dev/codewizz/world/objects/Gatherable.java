package dev.codewizz.world.objects;

import dev.codewizz.gfx.Renderer;
import dev.codewizz.world.GameObject;

public abstract class Gatherable extends GameObject {

    private boolean tasked = false;

    public Gatherable(String id) {
        super(id);
    }

    public abstract void gather();

    @Override
    public void update(float dt) {
    }

    public boolean isTasked() {
        return tasked;
    }

    public void setTasked(boolean tasked) {
        this.tasked = tasked;
    }
}
