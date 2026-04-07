package dev.codewizz.world.objects.behaviour.templates;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.badlogic.gdx.ai.btree.leaf.Wait;
import com.badlogic.gdx.ai.utils.random.UniformFloatDistribution;
import dev.codewizz.world.Entity;
import dev.codewizz.world.objects.Gatherable;
import dev.codewizz.world.objects.behaviour.TaskTemplate;
import dev.codewizz.world.objects.behaviour.leaves.ActionLeaf;
import dev.codewizz.world.objects.behaviour.leaves.MoveToLeaf;
import dev.codewizz.world.objects.behaviour.leaves.WaitArriveLeaf;
import dev.codewizz.world.objects.behaviour.pathfinding.NavAgent;

public class GatherTemplate implements TaskTemplate {

    private final Gatherable gatherable;

    public GatherTemplate(Gatherable gatherable) {
        this.gatherable = gatherable;
    }

    @Override
    public BehaviorTree<Entity> create(Entity entity) {
        return new BehaviorTree<>(
            new Sequence<>(
                new MoveToLeaf(NavAgent.graph.getCell(gatherable.getPosition())),
                new WaitArriveLeaf(),
                //new SetAnimationLeaf(Animations.GATHER),
                new Wait<>(new UniformFloatDistribution(2f, 4f)),
                new ActionLeaf<Entity>() {
                    @Override
                    public boolean action() {
                        gatherable.gather();
                        return true;
                    }
                }
            ),
            entity
        );
    }

    @Override
    public boolean canTake(Entity entity) {
        return true;
    }
}
