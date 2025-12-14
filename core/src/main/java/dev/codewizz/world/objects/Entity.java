package dev.codewizz.world.objects;

import com.badlogic.gdx.math.Vector3;
import dev.codewizz.world.GameObject;

public abstract class Entity extends GameObject {

    private final Vector3 velocity;

    public Entity(String id) {
        super(id);

        velocity = new Vector3();
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public boolean isMoving() {
        return false;
    }
}
