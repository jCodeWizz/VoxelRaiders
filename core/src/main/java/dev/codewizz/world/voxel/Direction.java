package dev.codewizz.world.voxel;

import com.badlogic.gdx.math.Vector3;

public enum Direction {
    UP(0, 1, 0, new Vector3(0, 1, 0)),
    DOWN(0, -1, 0, new Vector3(0, -1, 0)),
    NORTH(0, 0, -1, new Vector3(0, 0, -1)),
    SOUTH(0, 0, 1, new Vector3(0, 0, 1)),
    WEST(-1, 0, 0, new Vector3(-1, 0, 0)),
    EAST(1, 0, 0, new Vector3(1, 0, 0));

    public final int dx, dy, dz;
    public final Vector3 normal;

    Direction(int dx, int dy, int dz, Vector3 normal) {
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
        this.normal = normal;
    }

    public Vector3[] getFaceVertices(int x, int y, int z) {
        float fx = x;
        float fy = y;
        float fz = z;

        return switch (this) {
            case UP -> new Vector3[]{
                new Vector3(fx, fy + 1, fz),
                new Vector3(fx + 1, fy + 1, fz),
                new Vector3(fx + 1, fy + 1, fz + 1),
                new Vector3(fx, fy + 1, fz + 1)
            };
            case DOWN -> new Vector3[]{
                new Vector3(fx, fy, fz),
                new Vector3(fx, fy, fz + 1),
                new Vector3(fx + 1, fy, fz + 1),
                new Vector3(fx + 1, fy, fz)
            };
            case NORTH -> new Vector3[]{
                new Vector3(fx, fy, fz),
                new Vector3(fx + 1, fy, fz),
                new Vector3(fx + 1, fy + 1, fz),
                new Vector3(fx, fy + 1, fz)
            };
            case SOUTH -> new Vector3[]{
                new Vector3(fx + 1, fy, fz + 1),
                new Vector3(fx, fy, fz + 1),
                new Vector3(fx, fy + 1, fz + 1),
                new Vector3(fx + 1, fy + 1, fz + 1)
            };
            case WEST -> new Vector3[]{
                new Vector3(fx, fy, fz + 1),
                new Vector3(fx, fy, fz),
                new Vector3(fx, fy + 1, fz),
                new Vector3(fx, fy + 1, fz + 1)
            };
            case EAST -> new Vector3[]{
                new Vector3(fx + 1, fy, fz),
                new Vector3(fx + 1, fy, fz + 1),
                new Vector3(fx + 1, fy + 1, fz + 1),
                new Vector3(fx + 1, fy + 1, fz)
            };
        };
    }
}
