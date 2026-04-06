package dev.codewizz.world.objects.behaviour.leaves;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import dev.codewizz.world.Entity;
import dev.codewizz.world.objects.behaviour.pathfinding.NavAgent;
import dev.codewizz.world.objects.behaviour.pathfinding.NavCell;

public class MoveToLeaf extends LeafTask<Entity> {

    private NavCell cell;

    public MoveToLeaf(NavCell cell) {
        this.cell = cell;
    }

    @Override
    public Status execute() {
        Entity e = getObject();

        e.getAgent().setGoal(cell);
        boolean success = e.getAgent().navigate(NavAgent.graph.getCell(e.getPosition()));

        if (success) {
            return Status.SUCCEEDED;
        }

        return Status.FAILED;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        ((MoveToLeaf)task).cell = this.cell;
        return task;
    }
}
