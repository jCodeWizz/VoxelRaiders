package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.WUtils;
import dev.codewizz.world.inventory.Item;
import dev.codewizz.world.inventory.types.ItemType;

public class FallenTree extends Gatherable {

    private final ModelInstance instance;

    public FallenTree() {
        super("vxr:fallen-tree");

        instance = new ModelInstance(Assets.findModel(getId()));
    }

    @Override
    public void gather(Hermit hermit) {
        hermit.getInventory().addItem(new Item(ItemType.LOG, WUtils.getRandom(2, 4)));
        destroy();
    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderObjectInstance(this, instance);
    }
}
