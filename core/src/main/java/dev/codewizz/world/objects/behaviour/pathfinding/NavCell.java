package dev.codewizz.world.objects.behaviour.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultConnection;
import com.badlogic.gdx.utils.Array;

public class NavCell {

    public float x, y, z;
    public int index, indexX, indexZ;

    public int height;
    public float cost;
    public boolean walkable;

    public Array<Connection<NavCell>> connections;

    public NavCell(float x, float y, float z, int index, int indexX, int indexZ, int height, float cost, boolean walkable) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.index = index;
        this.indexX = indexX;
        this.indexZ = indexZ;
        this.height = height;
        this.cost = cost;
        this.walkable = walkable;

        connections = new Array<>();
    }

    public void addConnection(NavCell navCell) {
        connections.add(new NavConnection<>(this, navCell, (cost + navCell.cost)/2f));
        navCell.connections.add(new NavConnection<>(navCell, this, (cost + navCell.cost)/2f));
    }
}
