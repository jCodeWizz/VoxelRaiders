package dev.codewizz.world.objects;

import dev.codewizz.gfx.Renderer;
import dev.codewizz.world.inventory.types.ItemType;

public class SmallPile extends Storage {

    private ItemType storageType;

    public SmallPile() {
        super("vxr:small-pile");

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

    }
}
