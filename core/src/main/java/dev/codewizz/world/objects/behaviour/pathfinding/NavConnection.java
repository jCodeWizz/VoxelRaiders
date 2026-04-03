package dev.codewizz.world.objects.behaviour.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;

public class NavConnection<N> implements Connection<N> {

    protected N fromNode;
    protected N toNode;
    protected float cost;

    public NavConnection(N fromNode, N toNode, float cost) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.cost = cost;
    }

    @Override
    public float getCost() {
        return cost;
    }

    @Override
    public N getFromNode() {
        return fromNode;
    }

    @Override
    public N getToNode() {
        return toNode;
    }
}
