package dev.codewizz.world.objects.behaviour;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import dev.codewizz.world.Entity;
import dev.codewizz.world.objects.Hermit;

public interface TaskTemplate {

    BehaviorTree<Entity> create(Entity entity);
    boolean canTake(Entity entity);
}
