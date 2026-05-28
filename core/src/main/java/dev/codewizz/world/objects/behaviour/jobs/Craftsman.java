package dev.codewizz.world.objects.behaviour.jobs;

import dev.codewizz.main.Main;
import dev.codewizz.world.inventory.recipes.Recipe;
import dev.codewizz.world.inventory.types.ItemType;
import dev.codewizz.world.objects.Hermit;
import dev.codewizz.world.objects.behaviour.TaskTemplate;
import dev.codewizz.world.objects.behaviour.templates.ChoppingCraftTemplate;

public class Craftsman extends Job {

    private static final int PLANK_LIMIT = 30;
    private static final int FIREWOOD_LIMIT = 30;

    private static final ChoppingCraftTemplate CRAFT_PLANK = new ChoppingCraftTemplate(Recipe.CHOPPING_REGISTRY.get("vxr:planks"));
    private static final ChoppingCraftTemplate CRAFT_FIREWOOD = new ChoppingCraftTemplate(Recipe.CHOPPING_REGISTRY.get("vxr:firewood"));

    public Craftsman(Hermit hermit) {
        super(hermit);
    }

    @Override
    public TaskTemplate findNewTask() {

        if (Main.instance.getWorld().getSettlement().findItem(ItemType.PLANK).getSize() >= PLANK_LIMIT) {
            return CRAFT_PLANK;
        }

        if (Main.instance.getWorld().getSettlement().findItem(ItemType.FIREWOOD).getSize() >= FIREWOOD_LIMIT) {
            return CRAFT_FIREWOOD;
        }

        return super.findNewTask();
    }
}
