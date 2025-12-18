package dev.codewizz.world.objects.behaviour.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultConnection;
import com.badlogic.gdx.utils.Array;

public class NavCell {

    public float x, z;
    public int index;

    public int height;
    public float cost;
    public boolean walkable;

    public Array<Connection<NavCell>> connections;

    public NavCell(float x, float z, int index, int height, float cost, boolean walkable) {
        this.x = x;
        this.z = z;
        this.index = index;
        this.height = height;
        this.cost = cost;
        this.walkable = walkable;

        connections = new Array<>();
    }

    public void addConnection(NavCell navCell) {
        connections.add(new DefaultConnection<>(this, navCell));
        navCell.connections.add(new DefaultConnection<>(navCell, this));
    }
}
