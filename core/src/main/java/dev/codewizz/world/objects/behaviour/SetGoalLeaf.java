package dev.codewizz.world.objects.behaviour;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import dev.codewizz.world.Entity;

import java.util.Random;

public class SetGoalLeaf extends LeafTask<Entity> {

    @Override
    public Status execute() {
        Entity e = getObject();
        e.getAgent().setGoal(e.getAgent().getGraph().getCell(new Random().nextInt(9), new Random().nextInt(9)));
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        return task;
    }
}
