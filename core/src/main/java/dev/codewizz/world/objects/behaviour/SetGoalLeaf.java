package dev.codewizz.world.objects.behaviour;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import dev.codewizz.world.Entity;

import dev.codewizz.world.World;
import java.util.Random;

public class SetGoalLeaf extends LeafTask<Entity> {

    @Override
    public Status execute() {
        Entity e = getObject();
        e.getAgent().setGoal(e.getAgent().getGraph().getCell(new Random().nextInt(World.SIZE), new Random().nextInt(World.SIZE)));
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        return task;
    }
}
