package dev.codewizz.world.objects.behaviour.templates;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.badlogic.gdx.ai.btree.leaf.Wait;
import com.badlogic.gdx.utils.Array;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Logger;
import dev.codewizz.world.Entity;
import dev.codewizz.world.inventory.Item;
import dev.codewizz.world.inventory.recipes.Recipe;
import dev.codewizz.world.objects.Hermit;
import dev.codewizz.world.objects.Storage;
import dev.codewizz.world.objects.behaviour.TaskTemplate;
import dev.codewizz.world.objects.behaviour.jobs.Craftsman;
import dev.codewizz.world.objects.behaviour.leaves.ActionLeaf;
import dev.codewizz.world.objects.behaviour.leaves.MoveToLeaf;
import dev.codewizz.world.objects.behaviour.leaves.WaitArriveLeaf;
import dev.codewizz.world.objects.behaviour.pathfinding.NavAgent;

import java.util.ArrayList;
import java.util.List;

public class ChoppingCraftTemplate implements TaskTemplate {

    private final Recipe recipe;

    public ChoppingCraftTemplate(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public BehaviorTree<Entity> create(Entity entity) {
        Hermit hermit = (Hermit) entity;

        Array<Item> input = recipe.getInput();
        List<Storage> storages = new ArrayList<>();
        for (Item item : recipe.getInput()) {
            Storage storage = Main.instance.getWorld().getSettlement().findStorage(item, hermit);
            if (storage == null) {
                Logger.error("ERROR");
                throw new RuntimeException("Cannot find storage for item " + item.getType().getId());
            }

            storages.add(storage);
        }
        Sequence<Entity> root = new Sequence<>();

        for (int i = 0; i < storages.size(); i++) {
            Storage storage = storages.get(i);
            int index = i;
            root.addChild(new MoveToLeaf(NavAgent.graph.getCell(storage.getPosition())));
            root.addChild(new WaitArriveLeaf());
            root.addChild(new ActionLeaf<>() {
                @Override
                public boolean action() {
                    boolean a = storage.getInventory().removeItem(input.get(index));
                    if (a) {
                        hermit.getInventory().addItem(input.removeIndex(index));
                        return true;
                    }
                    return false;
                }
            });
        }

        root.addChild(new MoveToLeaf(NavAgent.graph.getCell(Main.instance.getWorld().getSettlement().getStations().get(0).getPosition())));
        root.addChild(new WaitArriveLeaf());
        root.addChild(new Wait<>(recipe.getDuration()));

        for (Item item : recipe.getInput()) {
            hermit.getInventory().removeItem(item);
        }
        for (Item item : recipe.getOutput()) {
            hermit.getInventory().addItem(item);
        }

        return new BehaviorTree<>(root, entity);
    }

    @Override
    public boolean canTake(Entity entity) {
        return ((Hermit) entity).getJob() instanceof Craftsman;
    }
}
