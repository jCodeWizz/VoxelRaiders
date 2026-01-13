package dev.codewizz.world.voxel;

import com.badlogic.gdx.graphics.Color;

public class VoxelData {

    public static final int SIZE = 2;

    public final static VoxelData GRASS = new VoxelData("vxr:grass", new Color(1f, 1f, 1f, 1f));
    public final static VoxelData DIRT = new VoxelData("vxr:dirt", new Color(1f, 1f, 1f, 1f));
    public final static VoxelData STONE = new VoxelData("vxr:stone", new Color(1f, 1f, 1f, 1f));
    public final static VoxelData SAND = new VoxelData("vxr:sand", new Color(1f, 1f, 1f, 1f));
    public final static VoxelData WATER = new VoxelData("vxr:water", new Color(0, 0, 1f, 0.5f));
    public final static VoxelData CLAY = new VoxelData("vxr:clay", new Color(1f, 1f, 1f, 1f));
    public final static VoxelData AIR = new VoxelData("vxr:air", new Color(1f, 1f, 1f, 1f));

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
