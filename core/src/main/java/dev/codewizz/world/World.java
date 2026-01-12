package dev.codewizz.world;

import dev.codewizz.world.voxel.Chunk;
import dev.codewizz.world.voxel.VoxelData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class World {

    public static final int SIZE = 256;

    private final List<GameObject> objects = new CopyOnWriteArrayList<>();
    private final Chunk[][] chunks = new Chunk[SIZE / Chunk.SIZE][SIZE / Chunk.SIZE];

    public World() {
        for (int xx = 0; xx < chunks.length; xx++ ) {
            for (int zz = 0; zz < chunks.length; zz++ ) {
                chunks[xx][zz] = new Chunk(xx * Chunk.SIZE - chunks.length/2*Chunk.SIZE, zz * Chunk.SIZE - chunks.length/2*Chunk.SIZE, xx, zz, this);
            }
        }


        for (int xx = 0; xx < chunks.length; xx++ ) {
            for (int zz = 0; zz < chunks.length; zz++ ) {
                chunks[xx][zz].buildMesh();
            }
        }
    }

    public VoxelData getVoxel(int x, int  y, int z) {

        System.out.println("Voxel: " + x + " " + y + " " + z);
        System.out.println("Size: " + Chunk.SIZE);

        return chunks[x / Chunk.SIZE][z / Chunk.SIZE].voxelData[x % Chunk.SIZE][y][z % Chunk.SIZE];
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

    public Chunk[][] getChunksToRender() {
        return chunks;
    }
}
