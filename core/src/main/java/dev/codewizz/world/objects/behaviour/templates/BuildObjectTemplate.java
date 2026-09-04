package dev.codewizz.world.objects.behaviour.templates;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.badlogic.gdx.math.Vector3;
import dev.codewizz.main.Main;
import dev.codewizz.main.Registers;
import dev.codewizz.world.Entity;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.GameObjectInfoShop;
import dev.codewizz.world.inventory.Item;
import dev.codewizz.world.objects.Hermit;
import dev.codewizz.world.objects.Placeholder;
import dev.codewizz.world.objects.behaviour.TaskTemplate;
import dev.codewizz.world.objects.behaviour.leaves.ActionLeaf;
import dev.codewizz.world.objects.behaviour.leaves.MoveToLeaf;
import dev.codewizz.world.objects.behaviour.leaves.WaitArriveLeaf;
import dev.codewizz.world.objects.behaviour.pathfinding.NavCell;

public class BuildObjectTemplate implements TaskTemplate {

    private final NavCell location;
    private final Placeholder placeholder;
    private final Item cost;

    public BuildObjectTemplate(NavCell location, Placeholder placeholder, Item cost) {
        this.location = location;
        this.placeholder = placeholder;
        this.cost = cost;
    }

    @Override
    public BehaviorTree<Entity> create(Entity entity) {
        Hermit hermit = (Hermit) entity;

        return new BehaviorTree<>(
            new Sequence<>(
                new MoveToLeaf(location),
                new WaitArriveLeaf(),
                new ActionLeaf<>() {
                    @Override
                    public boolean action() {
                        if (hermit.getInventory().containsItem(cost)) {
                            hermit.getInventory().removeItem(cost);
                            placeholder.addItem(cost);
                            return true;
                        } else {
                            return false;
                        }
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
