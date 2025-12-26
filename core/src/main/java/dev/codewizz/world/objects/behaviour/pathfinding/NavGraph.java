package dev.codewizz.world.objects.behaviour.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;
import dev.codewizz.main.Main;

public class NavGraph implements IndexedGraph<NavCell> {

    private NavCell[][] grid;

    public NavGraph() {

        grid = new NavCell[10][10];

        for (int i = 0; i < 100; i++) {

            int indexX = i % 10;
            int indexZ = i / 10;

            grid[indexX][indexZ] = new NavCell(indexX - 4.5f, indexZ - 4.5f, i, 0, 1f, true);

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
        return 100;
    }

    @Override
    public Array<Connection<NavCell>> getConnections(NavCell from) {
        return from.connections;
    }

    public NavCell getCell(int x, int z) {
        return grid[x][z];
    }
}
