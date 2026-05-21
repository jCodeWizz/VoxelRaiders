package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.WUtils;
import dev.codewizz.world.inventory.Item;
import dev.codewizz.world.inventory.ItemType;

public class Tree extends Gatherable {

    private final ModelInstance instance;

    public Tree() {
        super("vxr:tree");

        this.instance = new ModelInstance(Assets.findModel(getId()));
        this.getRotation().setFromAxis(Vector3.Y, WUtils.getRandom(0, 360));
        float scale = WUtils.RANDOM.nextFloat() * 0.3f + 0.8f;
        this.getScale().set(scale, scale, scale);
    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderObjectInstance(this, instance);
    }

    @Override
    public void gather(Hermit hermit) {
        Main.instance.getWorld().addObject(new TreeStump(getPosition()));
        hermit.getInventory().addItem(new Item(ItemType.LOG, 4));
        destroy();
    }
}
