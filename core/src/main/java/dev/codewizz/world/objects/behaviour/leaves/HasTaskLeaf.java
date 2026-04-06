package dev.codewizz.world.objects.behaviour.leaves;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import dev.codewizz.utils.Logger;
import dev.codewizz.world.Entity;
import dev.codewizz.world.objects.Hermit;
import dev.codewizz.world.objects.behaviour.TaskTemplate;

public class HasTaskLeaf extends LeafTask<Entity> {

    @Override
    public Status execute() {
        Entity e = getObject();

        if (e.hasTask()) {
            return Status.SUCCEEDED;
        }

        if (e.getTasks().notEmpty()) {
            e.setCurrentTask();
            return Status.SUCCEEDED;
        }

        TaskTemplate t = e.findNewTask();
        e.addTask(t.create(e));
        e.setCurrentTask();

        return Status.SUCCEEDED;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        return task;
    }
}
