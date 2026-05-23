package dev.codewizz.world.objects.behaviour.templates;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import dev.codewizz.main.Main;
import dev.codewizz.world.Entity;
import dev.codewizz.world.inventory.Item;
import dev.codewizz.world.objects.Hermit;
import dev.codewizz.world.objects.Storage;
import dev.codewizz.world.objects.behaviour.TaskTemplate;
import dev.codewizz.world.objects.behaviour.leaves.ActionLeaf;
import dev.codewizz.world.objects.behaviour.leaves.MoveToLeaf;
import dev.codewizz.world.objects.behaviour.pathfinding.NavAgent;

public class ClearInventoryTemplate implements TaskTemplate {

    @Override
    public BehaviorTree<Entity> create(Entity entity) {
        Hermit hermit = (Hermit) entity;
        Storage storage = Main.instance.getWorld().getSettlement().findStorage(hermit);

        return new BehaviorTree<>(
            new Sequence<>(
                new MoveToLeaf(NavAgent.graph.getCell(storage.getPosition())),
                new ActionLeaf<>() {
                    @Override
                    public boolean action() {

                        for (Item item : hermit.getInventory().getItems().values()) {
                            if (!storage.getInventory().isFull(item)) {
                                storage.getInventory().addItem(item);
                                hermit.getInventory().removeItem(item);
                            }
                        }

                        return true;
                    }
                }
            ),
            entity
        );
    }

    @Override
    public boolean canTake(Entity entity) {
        return !((Hermit) entity).getInventory().getItems().isEmpty();
    }
}
