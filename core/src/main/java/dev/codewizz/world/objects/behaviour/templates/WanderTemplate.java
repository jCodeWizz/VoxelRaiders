package dev.codewizz.world.objects.behaviour.templates;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.badlogic.gdx.math.MathUtils;
import dev.codewizz.utils.WUtils;
import dev.codewizz.world.Entity;
import dev.codewizz.world.objects.behaviour.TaskTemplate;
import dev.codewizz.world.objects.behaviour.leaves.MoveToLeaf;
import dev.codewizz.world.objects.behaviour.leaves.WaitArriveLeaf;
import dev.codewizz.world.objects.behaviour.pathfinding.NavAgent;
import dev.codewizz.world.objects.behaviour.pathfinding.NavCell;
import dev.codewizz.world.objects.behaviour.pathfinding.NavGraph;

public class WanderTemplate implements TaskTemplate {
    @Override
    public BehaviorTree<Entity> create(Entity entity) {
        NavCell current = NavAgent.graph.getCell(entity.getPosition());

        int indexX = MathUtils.clamp(current.indexX + WUtils.getRandom(-4, 4) * 2, 0, NavGraph.SIZE - 1);
        int indexZ = MathUtils.clamp(current.indexZ + WUtils.getRandom(-4, 4) * 2, 0, NavGraph.SIZE - 1);

        return new BehaviorTree<>(
            new Sequence<>(
                new MoveToLeaf(NavAgent.graph.getCell(indexX, indexZ)),
                new WaitArriveLeaf()
            ),
            entity
        );
    }
}
