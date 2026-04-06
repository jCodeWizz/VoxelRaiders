package dev.codewizz.world;

import dev.codewizz.input.MouseInput;
import dev.codewizz.world.settlement.Settlement;
import dev.codewizz.world.voxel.Chunk;
import dev.codewizz.world.voxel.VoxelData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class World {

    public static final int SIZE = 64*8;
    public static final int CHUNK_COUNT = SIZE / Chunk.SIZE;

    private final List<GameObject> objects = new CopyOnWriteArrayList<>();
    private final Chunk[][] chunks = new Chunk[CHUNK_COUNT][CHUNK_COUNT];

    private Settlement settlement;

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

    public void setVoxel(float x, float y, float z, VoxelData data) {
        x += (float) SIZE / VoxelData.SIZE / 2;
        z += (float) SIZE / VoxelData.SIZE / 2;

        int vx = (int) (x * VoxelData.SIZE);
        int vy = (int) (y * VoxelData.SIZE);
        int vz = (int) (z * VoxelData.SIZE);

        int chunkX = vx / Chunk.SIZE;
        int chunkZ = vz / Chunk.SIZE;

        int indexX = vx % Chunk.SIZE;
        int indexY = vy - 1;
        int indexZ = vz % Chunk.SIZE;

        chunks[chunkX][chunkZ].voxelData[indexX][indexY][indexZ] = data;
        chunks[chunkX][chunkZ].markDirty();

        if (indexX == 0 && chunkX > 0) { chunks[chunkX - 1][chunkZ].markDirty(); }
        if (indexZ == 0 && chunkZ > 0) { chunks[chunkX][chunkZ - 1].markDirty(); }
        if (indexX == Chunk.SIZE - 1 && chunkX < chunks.length - 1) { chunks[chunkX + 1][chunkZ].markDirty(); }
        if (indexZ == Chunk.SIZE - 1 && chunkZ < chunks.length - 1) { chunks[chunkX][chunkZ + 1].markDirty(); }
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

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }

    public Settlement getSettlement() {
        return settlement;
    }
}
