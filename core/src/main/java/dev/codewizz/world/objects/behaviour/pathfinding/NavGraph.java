package dev.codewizz.world.objects.behaviour.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;
import dev.codewizz.main.Main;
import dev.codewizz.world.World;
import dev.codewizz.world.voxel.VoxelData;

public class NavGraph implements IndexedGraph<NavCell> {

    public static final int SIZE = World.SIZE / VoxelData.SIZE;

    private final NavCell[][] grid;

    public NavGraph() {

        grid = new NavCell[SIZE][SIZE];

        for (int i = 0; i < SIZE * SIZE; i++) {

            int indexX = i % SIZE;
            int indexZ = i / SIZE;

            grid[indexX][indexZ] = new NavCell(indexX - (SIZE/2f) + 0.5f, 10.5f, indexZ - (SIZE/2f) + 0.5f, i, 0, 1f, true);

            if (indexX > 0) { grid[indexX][indexZ].addConnection(grid[indexX-1][indexZ]); }
            if (indexZ > 0) { grid[indexX][indexZ].addConnection(grid[indexX][indexZ-1]); }
        }
    }

    @Override
    public int getIndex(NavCell node) {
        return node.index;
    }

    @Override
    public int getNodeCount() {
        return SIZE * SIZE;
    }

    @Override
    public Array<Connection<NavCell>> getConnections(NavCell from) {
        return from.connections;
    }

    public NavCell getCell(int x, int z) {
        return grid[x][z];
    }
}
