package dev.codewizz.world.objects.behaviour.leaves;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import dev.codewizz.world.Entity;
import dev.codewizz.world.GameObject;

public class ActionLeaf<E extends GameObject> extends LeafTask<Entity> {

    @Override
    public Status execute() {

        boolean success = action();

        return success ? Status.SUCCEEDED : Status.FAILED;
    }

    public boolean action() {
        return false;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        return task;
    }
}
