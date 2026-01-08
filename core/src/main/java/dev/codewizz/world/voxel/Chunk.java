package dev.codewizz.world.voxel;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ShortArray;

public class Chunk {

    private final static int SIZE = 16;
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
                        addCube(b, x, y, z, data.getColour());
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

    private void addCube(MeshBuilder b, int x, int y, int z, Color color) {
        Vector3 v000 = new Vector3(x,     y,     z);
        Vector3 v100 = new Vector3(x + 1, y,     z);
        Vector3 v010 = new Vector3(x,     y + 1, z);
        Vector3 v110 = new Vector3(x + 1, y + 1, z);

        Vector3 v001 = new Vector3(x,     y,     z + 1);
        Vector3 v101 = new Vector3(x + 1, y,     z + 1);
        Vector3 v011 = new Vector3(x,     y + 1, z + 1);
        Vector3 v111 = new Vector3(x + 1, y + 1, z + 1);

        // +Y (top)
        b.rect(
            new MeshPartBuilder.VertexInfo().set(v010, new Vector3(0, 1, 0), color, null),
            new MeshPartBuilder.VertexInfo().set(v110, new Vector3(0, 1, 0), color, null),
            new MeshPartBuilder.VertexInfo().set(v111, new Vector3(0, 1, 0), color, null),
            new MeshPartBuilder.VertexInfo().set(v011, new Vector3(0, 1, 0), color, null)
        );

        // -Y (bottom)
        b.rect(
            new MeshPartBuilder.VertexInfo().set(v000, new Vector3(0, -1, 0), color, null),
            new MeshPartBuilder.VertexInfo().set(v001, new Vector3(0, -1, 0), color, null),
            new MeshPartBuilder.VertexInfo().set(v101, new Vector3(0, -1, 0), color, null),
            new MeshPartBuilder.VertexInfo().set(v100, new Vector3(0, -1, 0), color, null)
        );

        // +X
        b.rect(
            new MeshPartBuilder.VertexInfo().set(v100, new Vector3(1, 0, 0), color, null),
            new MeshPartBuilder.VertexInfo().set(v101, new Vector3(1, 0, 0), color, null),
            new MeshPartBuilder.VertexInfo().set(v111, new Vector3(1, 0, 0), color, null),
            new MeshPartBuilder.VertexInfo().set(v110, new Vector3(1, 0, 0), color, null)
        );

        // -X
        b.rect(
            new MeshPartBuilder.VertexInfo().set(v000, new Vector3(-1, 0, 0), color, null),
            new MeshPartBuilder.VertexInfo().set(v010, new Vector3(-1, 0, 0), color, null),
            new MeshPartBuilder.VertexInfo().set(v011, new Vector3(-1, 0, 0), color, null),
            new MeshPartBuilder.VertexInfo().set(v001, new Vector3(-1, 0, 0), color, null)
        );

        // +Z
        b.rect(
            new MeshPartBuilder.VertexInfo().set(v001, new Vector3(0, 0, 1), color, null),
            new MeshPartBuilder.VertexInfo().set(v011, new Vector3(0, 0, 1), color, null),
            new MeshPartBuilder.VertexInfo().set(v111, new Vector3(0, 0, 1), color, null),
            new MeshPartBuilder.VertexInfo().set(v101, new Vector3(0, 0, 1), color, null)
        );

        // -Z
        b.rect(
            new MeshPartBuilder.VertexInfo().set(v000, new Vector3(0, 0, -1), color, null),
            new MeshPartBuilder.VertexInfo().set(v100, new Vector3(0, 0, -1), color, null),
            new MeshPartBuilder.VertexInfo().set(v110, new Vector3(0, 0, -1), color, null),
            new MeshPartBuilder.VertexInfo().set(v010, new Vector3(0, 0, -1), color, null)
        );
    }


    private void fillChunk() {
        for (int xx = 0; xx < SIZE; xx++)  {
            for (int yy = 0; yy < SIZE; yy++)  {
                for (int zz = 0; zz < SIZE; zz++)  {
                    if (yy == 4) {
                        voxelData[xx][yy][zz] =  VoxelData.GRASS;
                    } else {
                        voxelData[xx][yy][zz] = VoxelData.AIR;
                    }
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
