package dev.codewizz.world.objects.behaviour.templates;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import dev.codewizz.main.Main;
import dev.codewizz.world.Entity;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.objects.behaviour.TaskTemplate;
import dev.codewizz.world.objects.behaviour.leaves.ActionLeaf;
import dev.codewizz.world.objects.behaviour.leaves.MoveToLeaf;
import dev.codewizz.world.objects.behaviour.leaves.WaitArriveLeaf;
import dev.codewizz.world.objects.behaviour.pathfinding.NavAgent;

public class ChangeObjectTemplate implements TaskTemplate {

    private final GameObject toReplace;
    private final GameObject result;

    public ChangeObjectTemplate(GameObject toReplace, GameObject result) {
        this.toReplace = toReplace;
        this.result = result;
    }

    @Override
    public BehaviorTree<Entity> create(Entity entity) {
        return new BehaviorTree<>(
            new Sequence<>(
                new MoveToLeaf(NavAgent.graph.getCell(toReplace.getPosition())),
                new WaitArriveLeaf(),
                new ActionLeaf<>() {
                    @Override
                    public boolean action() {
                        toReplace.destroy();
                        result.getPosition().set(toReplace.getPosition());
                        result.getRotation().set(toReplace.getRotation());
                        Main.instance.getWorld().addObject(result);
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
