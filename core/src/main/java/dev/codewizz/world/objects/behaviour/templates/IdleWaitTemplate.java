package dev.codewizz.world.objects.behaviour.templates;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.leaf.Wait;
import dev.codewizz.world.Entity;
import dev.codewizz.world.objects.behaviour.TaskTemplate;

public class IdleWaitTemplate implements TaskTemplate {

    private final float time;

    public IdleWaitTemplate(float time) {
        this.time = time;
    }

    @Override
    public BehaviorTree<Entity> create(Entity entity) {
        return new BehaviorTree<>(
            new Wait<>(time),
            entity
        );
    }
}
