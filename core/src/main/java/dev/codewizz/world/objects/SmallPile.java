package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.inventory.Item;
import dev.codewizz.world.inventory.types.ItemType;

import java.util.List;

public class SmallPile extends Storage implements IBuy{

    private ItemType storageType;
    private final ModelInstance instance;

    public SmallPile() {
        super("vxr:small-pile", 1);

        this.name = "Small Pile";
        this.description = "Can store infinte amounts of 1 type";

        this.instance = new ModelInstance(Assets.findModel(getId()));
    }

    @Override
    public boolean checkType(ItemType type) {
        if (inventory.getSize() == 0) {
            storageType = type;
            return true;
        } else {
            return storageType == type;
        }
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderObjectInstance(this, instance);
    }

    @Override
    public Model getIconModel() {
        return Assets.findModel(getId());
    }

    @Override
    public List<Item> getCosts() {
        return List.of(new Item(ItemType.LOG, 2), new Item(ItemType.PLANK, 3));
    }
}
