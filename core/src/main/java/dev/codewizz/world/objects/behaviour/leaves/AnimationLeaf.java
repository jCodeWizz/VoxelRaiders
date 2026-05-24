package dev.codewizz.world.objects.behaviour.leaves;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import dev.codewizz.utils.Logger;
import dev.codewizz.world.Entity;

public class AnimationLeaf extends LeafTask<Entity> {

    private String animation = null;
    private boolean loop = false;

    public AnimationLeaf(String animation) {
        this.animation = animation;
    }

    public AnimationLeaf(String animation, boolean loop) {
        this.animation = animation;
        this.loop = loop;
    }

    @Override
    public Status execute() {
        getObject().playAnimation(animation, loop);

        return Status.SUCCEEDED;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        ((AnimationLeaf)task).animation = animation;
        ((AnimationLeaf)task).loop = loop;
        return task;
    }
}
