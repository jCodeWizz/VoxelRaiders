package dev.codewizz.world.objects;

import dev.codewizz.world.GameObject;
import dev.codewizz.world.inventory.Inventory;
import dev.codewizz.world.inventory.Item;
import java.util.Collection;
import java.util.List;

public abstract class Storage extends GameObject {

    protected Inventory inventory;

    public Storage(String id) {
        super(id);
    }

    public boolean accepts(Collection<Item> items) {
        int slotsOver = inventory.getMaxSize() - inventory.getSize();
        for (Item item : items) {
            if (inventory.getItems().containsKey(item.getType().getId())) { continue; }
            if (slotsOver > 0) {
                slotsOver--;
            } else {
                return false;
            }
        }
        return true;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
