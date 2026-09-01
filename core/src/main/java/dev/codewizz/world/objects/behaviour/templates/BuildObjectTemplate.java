package dev.codewizz.world.objects.behaviour.templates;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import dev.codewizz.world.Entity;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.objects.behaviour.TaskTemplate;
import dev.codewizz.world.objects.behaviour.pathfinding.NavCell;

public class BuildObjectTemplate implements TaskTemplate {

    private final NavCell location;
    private final GameObject object;

    public BuildObjectTemplate(NavCell location, GameObject object) {
        this.location = location;
        this.object = object;
    }

    @Override
    public BehaviorTree<Entity> create(Entity entity) {
        return null;
    }

    @Override
    public boolean canTake(Entity entity) {
        return false;
    }
}
