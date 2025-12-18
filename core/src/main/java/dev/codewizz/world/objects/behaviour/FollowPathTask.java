package dev.codewizz.world.objects.behaviour;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import dev.codewizz.world.Entity;

public class FollowPathTask extends LeafTask<Entity> {

    @Override
    public Status execute() {
        Entity e = getObject();

        if (!e.isMoving()) {
            e.getVelocity().setZero();
            e.getAgent().getPath().clear();
            return Status.SUCCEEDED;
        }

        return Status.RUNNING;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        return task;
    }
}
