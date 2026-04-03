package dev.codewizz.input.result;

import com.badlogic.gdx.math.Vector3;
import dev.codewizz.world.voxel.Chunk;

public class PickChunkResult {

    private final Vector3 intersection;
    private Chunk chunk;
    private float distance;

    public PickChunkResult() {
        this.intersection = new Vector3();
        this.chunk = null;
        this.distance = Float.MAX_VALUE;
    }

    public Vector3 getIntersection() {
        return intersection;
    }

    public Chunk getChunk() {
        return chunk;
    }

    public void setChunk(Chunk chunk) {
        this.chunk = chunk;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
