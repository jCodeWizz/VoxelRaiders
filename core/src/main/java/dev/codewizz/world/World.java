package dev.codewizz.world;

import dev.codewizz.world.voxel.Chunk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class World {

    public static final int SIZE = 1000;

    private final List<GameObject> objects = new CopyOnWriteArrayList<>();
    private final List<Chunk> chunks = new CopyOnWriteArrayList<>();

    public World() {
        chunks.add(new Chunk(-4, -4));
        chunks.add(new Chunk(0, -4));
        chunks.add(new Chunk(-4, 0));
        chunks.add(new Chunk(0, 0));
    }

    public void addObject(GameObject object) {
        objects.add(object);
    }

    public void removeObject(GameObject object) {
        objects.remove(object);
    }

    public void update(float dt) {
        for (GameObject object : objects) {
            object.update(dt);
        }
    }

    public List<GameObject> getObjectsToRender() {
        return objects;
    }

    public List<Chunk> getChunksToRender() {
        return chunks;
    }
}
