package dev.codewizz.world.objects.behaviour.leaves;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import dev.codewizz.world.Entity;

public class WaitArriveLeaf extends LeafTask<Entity> {

    @Override
    public Status execute() {
        Entity e = getObject();

        if (e.isMoving()) {
            return Status.RUNNING;
        }
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        return task;
    }
}
