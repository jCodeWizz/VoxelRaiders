package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.WUtils;
import dev.codewizz.world.inventory.Item;
import dev.codewizz.world.inventory.types.ItemType;

public class Tree extends Gatherable {

    private final ModelInstance instance;
    private final ModelInstance choppedInstance;

    private boolean chopped = false;

    public Tree() {
        super("vxr:tree");

        this.instance = new ModelInstance(Assets.findModel(getId()));
        this.choppedInstance = new ModelInstance(Assets.findModel("vxr:tree-stump"));

        this.getRotation().setFromAxis(Vector3.Y, WUtils.getRandom(0, 360));
        float scale = WUtils.RANDOM.nextFloat() * 0.3f + 0.8f;
        this.getScale().set(scale, scale, scale);
    }

    @Override
    public void render(Renderer renderer) {
        if (chopped) {
            renderer.renderObjectInstance(this, choppedInstance);
        } else {
            renderer.renderObjectInstance(this, instance);
        }
    }

    @Override
    public void gather(Hermit hermit) {
        if (chopped) {
            destroy();
        } else {
            hermit.getInventory().addItem(new Item(ItemType.LOG, WUtils.getRandom(3, 5)));
            chopped = true;
        }
    }
}
