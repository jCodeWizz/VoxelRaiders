package dev.codewizz.world.settlement.structures;

import com.badlogic.gdx.math.Vector3;

public class Room {

    private boolean completed;

    private final Vector3 a;
    private final Vector3 b;

    public Room(Vector3 a, Vector3 b) {
        this.a = a;
        this.b = b;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Vector3 getA() {
        return a;
    }

    public Vector3 getB() {
        return b;
    }

    //TODO:
    // - requirements
    // - types

}
