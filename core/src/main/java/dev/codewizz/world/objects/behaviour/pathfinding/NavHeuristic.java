package dev.codewizz.world.objects.behaviour.pathfinding;

import com.badlogic.gdx.ai.pfa.Heuristic;

public class NavHeuristic implements Heuristic<NavCell> {

    @Override
    public float estimate(NavCell node, NavCell endNode) {
        return Math.abs(node.x - endNode.x) + Math.abs(node.z - endNode.z);
    }
}
