package dev.codewizz.world.objects.behaviour.templates;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.leaf.Wait;
import dev.codewizz.world.Entity;
import dev.codewizz.world.objects.behaviour.TaskTemplate;

public class GrazeTemplate implements TaskTemplate {

    @Override
    public BehaviorTree<Entity> create(Entity entity) {
        return new BehaviorTree<>(
            new Wait<>(2f),
            entity
        );
    }

    @Override
    public boolean canTake(Entity entity) {
        return true;
    }
}
