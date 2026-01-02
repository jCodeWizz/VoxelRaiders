package dev.codewizz.world.voxel;

import com.badlogic.gdx.graphics.Color;

public class VoxelData {

    public final static VoxelData GRASS = new VoxelData("vxr:grass", Color.GREEN);
    public final static VoxelData DIRT = new VoxelData("vxr:dirt", Color.BROWN);

    private final String id;
    private final Color colour;

    public VoxelData(String id, Color colour) {
        this.id = id;
        this.colour = colour;
    }

    public String getId() {
        return id;
    }

    public Color getColour() {
        return colour;
    }
}
