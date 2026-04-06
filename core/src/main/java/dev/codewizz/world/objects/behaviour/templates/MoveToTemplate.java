package dev.codewizz.world.objects.behaviour.templates;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import dev.codewizz.world.Entity;
import dev.codewizz.world.objects.behaviour.TaskTemplate;
import dev.codewizz.world.objects.behaviour.leaves.MoveToLeaf;
import dev.codewizz.world.objects.behaviour.leaves.WaitArriveLeaf;
import dev.codewizz.world.objects.behaviour.pathfinding.NavCell;

public class MoveToTemplate implements TaskTemplate {

    private final NavCell cell;

    public MoveToTemplate(NavCell cell) {
        this.cell = cell;
    }

    @Override
    public BehaviorTree<Entity> create(Entity entity) {
        return new BehaviorTree<>(
            new Sequence<>(
                new MoveToLeaf(cell),
                new WaitArriveLeaf()
            ),
            entity
        );
    }

    @Override
    public boolean canTake(Entity entity) {
        return true;
    }
}
