package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.math.Vector3;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.Logger;
import dev.codewizz.utils.WUtils;
import dev.codewizz.world.inventory.Item;
import dev.codewizz.world.inventory.ItemType;

public class Bush extends Gatherable {

    private final ModelInstance instance;

    private boolean hasBerries = true;

    public Bush() {
        super("vxr:bush");

        this.instance = new ModelInstance(Assets.findModel(getId()));
        this.getRotation().setFromAxis(Vector3.Y, WUtils.getRandom(0, 360));
        float scale = WUtils.RANDOM.nextFloat() * 0.5f + 1f;
        this.getScale().set(scale, scale, scale);
    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderObjectInstance(this, instance);
    }

    @Override
    public void gather(Hermit hermit) {
        if (hasBerries) {
            Node node = instance.getNode("berries");
            for (Node child : node.getChildren()) {
                for (NodePart childPart : child.parts) {
                    childPart.enabled = false;
                }
            }
            hasBerries = false;
            hermit.getInventory().addItem(new Item(ItemType.BERRIES, 4));
            Logger.log(hermit.getInventory());
        } else {
            destroy();
        }
    }
}
