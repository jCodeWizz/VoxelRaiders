package dev.codewizz.world.settlement.structures;

import com.badlogic.gdx.math.Vector3;

public class Room {

    private final Vector3 position;
    private final int width;
    private final int depth;

    public Room(Vector3 position, int width, int depth) {
        this.position = position;
        this.width = width;
        this.depth = depth;
    }

    public Vector3 getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getDepth() {
        return depth;
    }

    //TODO:
    // - requirements
    // - types

}
