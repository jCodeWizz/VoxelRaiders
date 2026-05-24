package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Logger;
import dev.codewizz.world.Entity;
import dev.codewizz.world.inventory.Inventory;
import dev.codewizz.world.objects.behaviour.TaskTemplate;
import dev.codewizz.world.objects.behaviour.templates.ClearInventoryTemplate;
import dev.codewizz.world.objects.behaviour.templates.IdleWaitTemplate;

public class Hermit extends Entity {

    private static final IdleWaitTemplate WAIT = new IdleWaitTemplate(2f, 5f);
    private static final ClearInventoryTemplate CLEAR = new ClearInventoryTemplate();

    private static final Material MATERIAL = new Material(ColorAttribute.createDiffuse(new Color(0.1f, 0.93f, 0.95f, 1.0f)));
    private static final Model MODEL = new ModelBuilder().createBox(0.8f, 1.5f, 0.8f, MATERIAL,VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

    private Inventory inventory;

    public Hermit() {
        super("vxr:hermit");

        instance = new ModelInstance(MODEL);
        getSize().set(0.8f, 1.5f, 0.8f);
        inventory = new Inventory(5);
    }

    @Override
    public TaskTemplate findNewTask() {

        if (inventory.getSize() > 0) {
            return CLEAR;
        }

        TaskTemplate next = null;
        for (TaskTemplate t : Main.instance.getWorld().getSettlement().getTasks()) {
            if (t.canTake(this)) {
                next = t;
                break;
            }
        }

        if (next != null) {
            Main.instance.getWorld().getSettlement().getTasks().removeValue(next, false);
            return next;
        }

        return WAIT;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
