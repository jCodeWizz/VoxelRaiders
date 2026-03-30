package dev.codewizz.world;

import dev.codewizz.world.voxel.Chunk;
import dev.codewizz.world.voxel.VoxelData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class World {

    public static final int SIZE = 64;

    private final List<GameObject> objects = new CopyOnWriteArrayList<>();
    private final Chunk[][] chunks = new Chunk[SIZE / Chunk.SIZE][SIZE / Chunk.SIZE];

    public World() {
        for (int xx = 0; xx < chunks.length; xx++ ) {
            for (int zz = 0; zz < chunks.length; zz++ ) {
                chunks[xx][zz] = new Chunk((xx * Chunk.SIZE - chunks.length/2*Chunk.SIZE)/VoxelData.SIZE, (zz * Chunk.SIZE - chunks.length/2*Chunk.SIZE)/VoxelData.SIZE, xx, zz, this);
            }
        }

        for (int xx = 0; xx < chunks.length; xx++ ) {
            for (int zz = 0; zz < chunks.length; zz++ ) {
                chunks[xx][zz].buildMesh();
            }
        }
    }

    public void setVoxel(int x, int y, int z, VoxelData data) {
        int chunkX = x / Chunk.SIZE;
        int chunkZ = z / Chunk.SIZE;
        int indexX = x  % Chunk.SIZE;
        int indexZ = z  % Chunk.SIZE;

        chunks[chunkX][chunkZ].voxelData[indexX][y][indexZ] = data;
        chunks[chunkX][chunkZ].buildMesh();

        if (indexX == 0 && chunkX > 0) { chunks[chunkX - 1][chunkZ].buildMesh(); }
        if (indexZ == 0 && chunkZ > 0) { chunks[chunkX][chunkZ - 1].buildMesh(); }
        if (indexX == Chunk.SIZE - 1 && chunkX < chunks.length - 1) { chunks[chunkX + 1][chunkZ].buildMesh(); }
        if (indexZ == Chunk.SIZE - 1 && chunkZ < chunks.length - 1) { chunks[chunkX][chunkZ + 1].buildMesh(); }
    }

    public VoxelData getVoxel(int x, int  y, int z) {
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
