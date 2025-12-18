package dev.codewizz.world.objects.behaviour.pathfinding;

import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Queue;
import dev.codewizz.world.Entity;

public class NavAgent {

    private final static NavHeuristic heuristic = new NavHeuristic();
    private final static NavGraph graph = new NavGraph();
    private final static IndexedAStarPathFinder<NavCell> pathFinder = new IndexedAStarPathFinder<>(graph);

    private final Queue<NavCell> path;
    private NavCell goal;
    private NavCell previous;

    private final Entity e;

    public NavAgent(Entity e) {
        path = new Queue<>();
        this.e = e;
    }

    public void update(float dt) {
        if (path.size > 0) {
            NavCell target = path.first();
            if (Vector3.dst2(e.getPosition().x, e.getPosition().y, e.getPosition().z, target.x, e.getPosition().y, target.z) < 0.0004f) {
                reach();
            }
        }
    }

    public void setGoal(NavCell goal) {
        this.goal = goal;
    }

    public boolean navigate(NavCell start) {
        previous = start;
        path.clear();

        GraphPath<NavCell> path = new DefaultGraphPath<>();
        boolean success = pathFinder.searchNodePath(start, goal, heuristic, path);

        if (success) {
            for (NavCell navCell : path) {
                this.path.addLast(navCell);
            }

            this.path.removeFirst();

            setSpeedToNextGoal();
        }

        return success;
    }

    private void reach() {
        NavCell next = path.first();
        this.e.getPosition().set(next.x, this.e.getPosition().y, next.z);

        this.previous = next;
        path.removeFirst();

        if (path.size == 0) {
            e.getVelocity().setZero();
            path.clear();
        } else {
            setSpeedToNextGoal();
        }
    }

    private void setSpeedToNextGoal() {
        NavCell next = path.first();
        float angle = MathUtils.atan2(next.z - e.getPosition().z, next.x - e.getPosition().x);
        e.getVelocity().set(MathUtils.cos(angle) * e.getSpeed(), 0f, MathUtils.sin(angle) *  e.getSpeed());
    }

    public Queue<NavCell> getPath() {
        return path;
    }

    public NavGraph getGraph() {
        return graph;
    }

    public NavCell getGoal() {
        return goal;
    }
}
