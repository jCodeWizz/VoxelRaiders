package dev.codewizz.world;

import com.badlogic.gdx.math.Vector3;
import dev.codewizz.input.MouseInput;
import dev.codewizz.utils.Logger;
import dev.codewizz.utils.SimplexNoise;
import dev.codewizz.world.objects.Bush;
import dev.codewizz.world.objects.Tree;
import dev.codewizz.world.settlement.Settlement;
import dev.codewizz.world.voxel.Chunk;
import dev.codewizz.world.voxel.VoxelData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class World {

    public static final int SIZE = 64;
    public static final int CHUNK_COUNT = SIZE / Chunk.SIZE;

    private final List<GameObject> objects = new CopyOnWriteArrayList<>();
    private final Chunk[][] chunks = new Chunk[CHUNK_COUNT][CHUNK_COUNT];

    private static SimplexNoise NOISE = new SimplexNoise(0);

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

        generateFeatures();
    }

    public void generateFeatures() {
        NOISE = new SimplexNoise(0);
        int half = SIZE / VoxelData.SIZE / 2;

        for (int xx = 1; xx < SIZE / VoxelData.SIZE; xx++ ) {
            for (int zz = 1; zz < SIZE / VoxelData.SIZE; zz++ ) {
                float noise = (float) (NOISE.noise((xx)*50, zz*50)+1.0) * 2f;

                if (noise < 0.175f) {
                    GameObject object = new Tree();
                    object.getPosition().set(xx - half, 10.75f, zz - half);
                    addObject(object);
                } else {
                    noise = (float) (NOISE.noise((xx/10f), zz/10f)+1.0) * 2f;
                    if (noise < 0.5f) {
                        GameObject object = new Bush();
                        object.getPosition().set(xx - half + 0.25f, 10.75f, zz - half + 0.25f);
                        addObject(object);
                    }
                }
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

    public List<GameObject> getObjectsWithinBounds(Vector3 min, Vector3 max) {
        ArrayList<GameObject> objectsWithinBounds = new ArrayList<>();

        for (GameObject obj : objects) {
            Vector3 p = obj.getPosition();

            if (p.x >= min.x && p.x <= max.x &&
                p.z >= min.z && p.z <= max.z) {
                objectsWithinBounds.add(obj);
            }
        }

        return objectsWithinBounds;
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
