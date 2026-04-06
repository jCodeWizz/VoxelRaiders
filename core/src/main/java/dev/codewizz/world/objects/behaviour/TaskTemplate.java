package dev.codewizz.world.objects.behaviour;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import dev.codewizz.world.Entity;

public interface TaskTemplate {

    BehaviorTree<Entity> create(Entity entity);
}
