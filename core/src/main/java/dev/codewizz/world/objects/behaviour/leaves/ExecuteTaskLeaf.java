package dev.codewizz.world.objects.behaviour.leaves;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import dev.codewizz.utils.Logger;
import dev.codewizz.world.Entity;
import dev.codewizz.world.objects.Hermit;

public class ExecuteTaskLeaf extends LeafTask<Entity> {

    @Override
    public Status execute() {
        Entity e = getObject();

        e.getCurrentTask().step();
        Status status = e.getCurrentTask().getStatus();

        if (status == Status.SUCCEEDED) {
            e.finishCurrentTask();
            return Status.SUCCEEDED;
        }

        if (status == Status.FAILED | status == Status.FAILED) {
            //TODO: re-add task back to either settlement queue or hermit queue
            return Status.FAILED;
        }

        return Status.RUNNING;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        return task;
    }
}
