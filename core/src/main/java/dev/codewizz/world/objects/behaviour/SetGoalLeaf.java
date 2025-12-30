package dev.codewizz.world.objects.behaviour;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.MathUtils;
import dev.codewizz.world.Entity;

import dev.codewizz.world.World;
import java.util.Random;

public class SetGoalLeaf extends LeafTask<Entity> {

    private final static Random random = new Random();

    @Override
    public Status execute() {
        Entity e = getObject();

        int navX = MathUtils.clamp(MathUtils.floor(e.getPosition().x + (World.SIZE/2f)) + random.nextInt(8) - 4, 0, World.SIZE-1);
        int navY = MathUtils.clamp(MathUtils.floor(e.getPosition().z + (World.SIZE/2f)) + random.nextInt(8) - 4, 0, World.SIZE-1);

        e.getAgent().setGoal(e.getAgent().getGraph().getCell(navX, navY));
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        return task;
    }
}
