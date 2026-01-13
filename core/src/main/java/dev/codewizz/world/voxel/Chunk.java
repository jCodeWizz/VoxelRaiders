package dev.codewizz.world.voxel;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import dev.codewizz.utils.SimplexNoise;
import dev.codewizz.world.World;

import static com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder.VertexInfo;


public class Chunk {

    public final static int SIZE = 32;
    public final static int HEIGHT = 256;
    private final static Material MATERIAL = new Material(ColorAttribute.createDiffuse(Color.WHITE));
    private final World world;

    private final int x, z, indexX, indexZ;
    public final VoxelData[][][] voxelData = new VoxelData[SIZE][HEIGHT][SIZE];

    private ModelInstance instance;
    private boolean dirty;

    public Chunk(int x, int z, int indexX, int indexZ, World world) {
        this.x = x;
        this.z = z;
        this.indexX = indexX;
        this.indexZ = indexZ;
        this.world = world;

        fillChunk();
    }

    public void buildMesh() {

        MeshBuilder b = new MeshBuilder();
        b.begin(VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.ColorUnpacked, GL20.GL_TRIANGLES);

        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                for (int z = 0; z < SIZE; z++) {
                    VoxelData data = voxelData[x][y][z];

                    if (!data.getId().equals(VoxelData.AIR.getId())) {
                        buildVoxel(b, x/4f, y/4f, z/4f, x, y, z, data.getColour());
                    }
                }
            }
        }

        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        modelBuilder.part("chunk", b.end(), GL20.GL_TRIANGLES, MATERIAL);
        instance = new ModelInstance(modelBuilder.end());
        dirty = false;
    }

    private void buildVoxel(MeshBuilder b, float x, float y, float z, int indexX, int indexY, int indexZ, Color color) {
        if (indexY == HEIGHT - 1 || voxelData[indexX][indexY+1][indexZ].getId().equals(VoxelData.AIR.getId())) {
            VertexInfo v010 = new VertexInfo().set(new Vector3(x + this.x, y+0.25f, z + this.z), new Vector3(0f, 1f,  0f), color, null);
            VertexInfo v110 = new VertexInfo().set(new Vector3(x+0.25f + this.x, y+0.25f, z + this.z), new Vector3(0f, 1f,  0f), color, null);
            VertexInfo v111 = new VertexInfo().set(new Vector3(x+0.25f + this.x, y+0.25f, z+0.25f + this.z), new Vector3(0f, 1f,  0f), color, null);
            VertexInfo v011 = new VertexInfo().set(new Vector3(x + this.x, y+0.25f, z+0.25f + this.z), new Vector3(0f, 1f,  0f), color, null);

            b.rect(v010, v011, v111, v110);
        }

        if (indexY == 0 || voxelData[indexX][indexY-1][indexZ].getId().equals(VoxelData.AIR.getId())) {
            VertexInfo v000 = new VertexInfo().set(new Vector3(x + this.x, y, z + this.z), new Vector3(0f, -1f,  0f), color, null);
            VertexInfo v100 = new VertexInfo().set(new Vector3(x+0.25f + this.x, y, z + this.z), new Vector3(0f, -1f,  0f), color, null);
            VertexInfo v101 = new VertexInfo().set(new Vector3(x+0.25f + this.x, y, z+0.25f + this.z), new Vector3(0f, -1f,  0f), color, null);
            VertexInfo v001 = new VertexInfo().set(new Vector3(x + this.x, y, z+0.25f + this.z), new Vector3(0f, -1f,  0f), color, null);

            b.rect(v000, v100, v101, v001);
        }

        if (indexZ + (this.indexZ*SIZE) == 0 || world.getVoxel(indexX + (this.indexX * SIZE), indexY, indexZ - 1 + (this.indexZ*SIZE)).getId().equals(VoxelData.AIR.getId())) {
            VertexInfo v000 = new VertexInfo().set(new Vector3(x + this.x, y, z + this.z), new Vector3(0f, 0f,  -1f), color, null);
            VertexInfo v010 = new VertexInfo().set(new Vector3(x + this.x, y+0.25f, z + this.z), new Vector3(0f, 0f,  -1f), color, null);
            VertexInfo v110 = new VertexInfo().set(new Vector3(x+0.25f + this.x, y+0.25f, z + this.z), new Vector3(0f, 0f,  -1f), color, null);
            VertexInfo v100 = new VertexInfo().set(new Vector3(x+0.25f + this.x, y, z + this.z), new Vector3(0f, 0f,  -1f), color, null);

            b.rect(v000, v010, v110, v100);
        }

        if (indexZ + (this.indexZ*SIZE) == World.SIZE - 1 || world.getVoxel(indexX + (this.indexX * SIZE), indexY, indexZ + 1 + (this.indexZ*SIZE)).getId().equals(VoxelData.AIR.getId())) {
            VertexInfo v001 = new VertexInfo().set(new Vector3(x + this.x, y, z+0.25f + this.z), new Vector3(0f, 0f,  1f), color, null);
            VertexInfo v011 = new VertexInfo().set(new Vector3(x + this.x, y+0.25f, z+0.25f + this.z), new Vector3(0f, 0f,  1f), color, null);
            VertexInfo v111 = new VertexInfo().set(new Vector3(x+0.25f + this.x, y+0.25f, z+0.25f + this.z), new Vector3(0f, 0f,  1f), color, null);
            VertexInfo v101 = new VertexInfo().set(new Vector3(x+0.25f + this.x, y, z+0.25f + this.z), new Vector3(0f, 0f,  1f), color, null);

            b.rect(v001, v101, v111, v011);
        }

        if (indexX + (this.indexX * SIZE) == World.SIZE - 1 || world.getVoxel(indexX + 1 + (this.indexX * SIZE), indexY, indexZ + (this.indexZ*SIZE)).getId().equals(VoxelData.AIR.getId())) {
            VertexInfo v100 = new VertexInfo().set(new Vector3(x+0.25f + this.x, y, z + this.z), new Vector3(1f, 0f,  0f), color, null);
            VertexInfo v110 = new VertexInfo().set(new Vector3(x+0.25f + this.x, y+0.25f, z + this.z), new Vector3(1f, 0f,  0f), color, null);
            VertexInfo v111 = new VertexInfo().set(new Vector3(x+0.25f + this.x, y+0.25f, z+0.25f + this.z), new Vector3(1f, 0f,  0f), color, null);
            VertexInfo v101 = new VertexInfo().set(new Vector3(x+0.25f + this.x, y, z+0.25f + this.z), new Vector3(1f, 0f,  0f), color, null);

            b.rect(v100, v110, v111, v101);
        }

        if (indexX + (this.indexX * SIZE) == 0 || world.getVoxel(indexX - 1 + (this.indexX * SIZE), indexY, indexZ + (this.indexZ*SIZE)).getId().equals(VoxelData.AIR.getId())) {
            VertexInfo v000 = new VertexInfo().set(new Vector3(x + this.x, y, z + this.z), new Vector3(-1f, 0f,  0f), color, null);
            VertexInfo v010 = new VertexInfo().set(new Vector3(x + this.x, y+0.25f, z + this.z), new Vector3(-1f, 0f,  0f), color, null);
            VertexInfo v011 = new VertexInfo().set(new Vector3(x + this.x, y+0.25f, z+0.25f + this.z), new Vector3(-1f, 0f,  0f), color, null);
            VertexInfo v001 = new VertexInfo().set(new Vector3(x + this.x, y, z+0.25f + this.z), new Vector3(-1f, 0f,  0f), color, null);

            b.rect(v000, v001, v011, v010);
        }
    }

    private void fillChunk() {
        for (int xx = 0; xx < SIZE; xx++) {
            for (int yy = 0; yy < HEIGHT; yy++) {
                for (int zz = 0; zz < SIZE; zz++) {
                    voxelData[xx][yy][zz] = VoxelData.AIR;
                }
            }
        }

        for (int xx = 0; xx < SIZE; xx++) {
            for (int zz = 0; zz < SIZE; zz++) {
                int y = 25 + (int) (((SimplexNoise.noise((this.indexX*SIZE + xx)/50.0, (this.indexZ*SIZE + zz)/50.0) + 1) / 2.0) * 16.0);
                voxelData[xx][y][zz] = VoxelData.GRASS;
                for (int yy = y - 5; yy < y; yy++) {
                    voxelData[xx][yy][zz] = VoxelData.DIRT;
                }
                for (int  yy = 0; yy < y - 5; yy++) {
                    voxelData[xx][yy][zz] = VoxelData.STONE;
                }
            }
        }
    }

    public boolean isDirty() {
        return dirty;
    }

    public ModelInstance getModelInstance() {
        return instance;
    }
}
