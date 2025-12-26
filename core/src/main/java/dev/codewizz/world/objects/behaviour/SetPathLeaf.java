package dev.codewizz.world.objects.behaviour;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.steer.utils.paths.LinePath;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import dev.codewizz.world.Entity;
import dev.codewizz.world.World;
import dev.codewizz.world.objects.behaviour.pathfinding.NavCell;

public class SetPathLeaf extends LeafTask<Entity> {

    @Override
    public Status execute() {
        Entity e = getObject();

        int navX = MathUtils.floor(e.getPosition().x + (World.SIZE/2f));
        int navY = MathUtils.floor(e.getPosition().z + (World.SIZE/2f));


        boolean success =  e.getAgent().navigate(e.getAgent().getGraph().getCell(navX, navY));

        return Status.SUCCEEDED;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        return task;
    }
}
