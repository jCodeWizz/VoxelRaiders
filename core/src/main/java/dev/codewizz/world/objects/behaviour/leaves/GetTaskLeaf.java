package dev.codewizz.world.objects.behaviour.leaves;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import dev.codewizz.utils.Logger;
import dev.codewizz.world.Entity;
import dev.codewizz.world.objects.behaviour.TaskTemplate;

public class GetTaskLeaf extends LeafTask<Entity> {

    @Override
    public Status execute() {
        Entity e = getObject();



        return Status.FAILED;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        return task;
    }
}
