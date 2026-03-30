package dev.codewizz.world.voxel;

import com.badlogic.gdx.graphics.Color;

public class VoxelData {

    public static final int SIZE = 2;

    public final static VoxelData GRASS = new VoxelData("vxr:grass", color(82, 191, 144), color(73, 171, 129), color(65, 152, 115), color(57, 133, 100), color(49,  114, 86));
    public final static VoxelData DIRT = new VoxelData("vxr:dirt", color(130,97,  77), color(110, 78, 60), color(88, 59, 42));
    public final static VoxelData STONE = new VoxelData("vxr:stone", color(229, 227,  227), color(203, 200, 199), color(178, 172, 172), color(152, 145, 144), color(126, 117, 116));
    public final static VoxelData SAND = new VoxelData("vxr:sand", new Color(1f, 1f, 1f, 1f));
    public final static VoxelData WATER = new VoxelData("vxr:water", new Color(0, 0, 1f, 0.5f));
    public final static VoxelData CLAY = new VoxelData("vxr:clay", new Color(1f, 1f, 1f, 1f));
    public final static VoxelData AIR = new VoxelData("vxr:air", new Color(1f, 1f, 1f, 1f));

    private final String id;
    private final Color[] colours;

    public VoxelData(String id, Color colour) {
        this.id = id;
        this.colours = new Color[] {colour};
    }

    public VoxelData(String id, Color... colours) {
        this.id = id;
        this.colours = colours;
    }

    public String getId() {
        return id;
    }

    public Color getColour(int meta) {
        return colours[meta % colours.length];
    }

    private static Color color(int r, int g, int b) {
        return new Color(r / 255.0f, g / 255.0f, b / 255.0f, 1.0f);
    }
}
