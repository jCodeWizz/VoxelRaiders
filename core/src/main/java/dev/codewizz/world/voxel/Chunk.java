package dev.codewizz.world.voxel;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

import static com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder.VertexInfo;


public class Chunk {

    private final static int SIZE = 2;
    private final static Material MATERIAL = new Material(ColorAttribute.createDiffuse(Color.WHITE));


    private final int x, z;
    private final VoxelData[][][] voxelData = new VoxelData[SIZE][SIZE][SIZE];

    private ModelInstance instance;
    private boolean dirty;

    public Chunk(int x, int z) {
        fillChunk();
        this.x = x;
        this.z = z;

        buildMesh();
    }

    public void buildMesh() {

        MeshBuilder b = new MeshBuilder();
        b.begin(VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.ColorUnpacked, GL20.GL_TRIANGLES);

        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < SIZE; z++) {
                    VoxelData data = voxelData[x][y][z];

                    if (!data.getId().equals(VoxelData.AIR.getId())) {
                        buildVoxel(b, x, y, z, data.getColour());
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

    private void buildVoxel(MeshBuilder b, int x, int y, int z, Color color) {
        if (y == SIZE - 1 || voxelData[x][y+1][z].getId().equals(VoxelData.AIR.getId())) {
            VertexInfo v010 = new VertexInfo().set(new Vector3(x, y+1f, z), new Vector3(0f, 1f,  0f), color, null);
            VertexInfo v110 = new VertexInfo().set(new Vector3(x+1f, y+1f, z), new Vector3(0f, 1f,  0f), color, null);
            VertexInfo v111 = new VertexInfo().set(new Vector3(x+1f, y+1f, z+1f), new Vector3(0f, 1f,  0f), color, null);
            VertexInfo v011 = new VertexInfo().set(new Vector3(x, y+1f, z+1f), new Vector3(0f, 1f,  0f), color, null);

            b.rect(v010, v011, v111, v110);
        }

        if (y == 0 || voxelData[x][y-1][z].getId().equals(VoxelData.AIR.getId())) {
            VertexInfo v000 = new VertexInfo().set(new Vector3(x, y, z), new Vector3(0f, -1f,  0f), color, null);
            VertexInfo v100 = new VertexInfo().set(new Vector3(x+1f, y, z), new Vector3(0f, -1f,  0f), color, null);
            VertexInfo v101 = new VertexInfo().set(new Vector3(x+1f, y, z+1f), new Vector3(0f, -1f,  0f), color, null);
            VertexInfo v001 = new VertexInfo().set(new Vector3(x, y, z+1f), new Vector3(0f, -1f,  0f), color, null);

            b.rect(v000, v100, v101, v001);
        }

        if (z == 0 || voxelData[x][y][z-1].getId().equals(VoxelData.AIR.getId())) {
            VertexInfo v000 = new VertexInfo().set(new Vector3(x, y, z), new Vector3(0f, 0f,  -1f), color, null);
            VertexInfo v010 = new VertexInfo().set(new Vector3(x, y+1f, z), new Vector3(0f, 0f,  -1f), color, null);
            VertexInfo v110 = new VertexInfo().set(new Vector3(x+1f, y+1f, z), new Vector3(0f, 0f,  -1f), color, null);
            VertexInfo v100 = new VertexInfo().set(new Vector3(x+1f, y, z), new Vector3(0f, 0f,  -1f), color, null);

            b.rect(v000, v010, v110, v100);
        }

        if (z == SIZE - 1 || voxelData[x][y][z+1].getId().equals(VoxelData.AIR.getId())) {
            VertexInfo v001 = new VertexInfo().set(new Vector3(x, y, z+1f), new Vector3(0f, 0f,  1f), color, null);
            VertexInfo v011 = new VertexInfo().set(new Vector3(x, y+1f, z+1f), new Vector3(0f, 0f,  1f), color, null);
            VertexInfo v111 = new VertexInfo().set(new Vector3(x+1f, y+1f, z+1f), new Vector3(0f, 0f,  1f), color, null);
            VertexInfo v101 = new VertexInfo().set(new Vector3(x+1f, y, z+1f), new Vector3(0f, 0f,  1f), color, null);

            b.rect(v001, v101, v111, v011);
        }

        if (x == SIZE - 1 || voxelData[x+1][y][z].getId().equals(VoxelData.AIR.getId())) {
            VertexInfo v100 = new VertexInfo().set(new Vector3(x+1f, y, z), new Vector3(1f, 0f,  0f), color, null);
            VertexInfo v110 = new VertexInfo().set(new Vector3(x+1f, y+1f, z), new Vector3(1f, 0f,  0f), color, null);
            VertexInfo v111 = new VertexInfo().set(new Vector3(x+1f, y+1f, z+1f), new Vector3(1f, 0f,  0f), color, null);
            VertexInfo v101 = new VertexInfo().set(new Vector3(x+1f, y, z+1f), new Vector3(1f, 0f,  0f), color, null);

            b.rect(v100, v110, v111, v101);
        }

        if (x == 0 || voxelData[x-1][y][z].getId().equals(VoxelData.AIR.getId())) {
            VertexInfo v000 = new VertexInfo().set(new Vector3(x, y, z), new Vector3(-1f, 0f,  0f), color, null);
            VertexInfo v010 = new VertexInfo().set(new Vector3(x, y+1f, z), new Vector3(-1f, 0f,  0f), color, null);
            VertexInfo v011 = new VertexInfo().set(new Vector3(x, y+1f, z+1f), new Vector3(-1f, 0f,  0f), color, null);
            VertexInfo v001 = new VertexInfo().set(new Vector3(x, y, z+1f), new Vector3(-1f, 0f,  0f), color, null);

            b.rect(v000, v001, v011, v010);
        }
    }


    private void fillChunk() {
        for (int xx = 0; xx < SIZE; xx++) {
            for (int yy = 0; yy < SIZE; yy++) {
                for (int zz = 0; zz < SIZE; zz++) {
                    voxelData[xx][yy][zz] = VoxelData.AIR;
                }
            }
        }

        voxelData[1][1][1] = VoxelData.GRASS;
        voxelData[0][0][0] = VoxelData.DIRT;
        voxelData[0][0][1] = VoxelData.DIRT;
        voxelData[1][0][0] = VoxelData.DIRT;
        voxelData[1][0][1] = VoxelData.DIRT;
    }

public boolean isDirty() {
    return dirty;
}

public ModelInstance getModelInstance() {
    return instance;
}
}
