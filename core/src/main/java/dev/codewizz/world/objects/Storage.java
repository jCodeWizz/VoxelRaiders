package dev.codewizz.world.objects;

import dev.codewizz.main.Main;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.inventory.Inventory;
import dev.codewizz.world.inventory.Item;
import dev.codewizz.world.inventory.types.ItemType;
import java.util.Collection;
import java.util.List;

public abstract class Storage extends GameObject {

    protected Inventory inventory;

    public Storage(String id, int size) {
        super(id);

        inventory = new Inventory(size);
        Main.instance.getWorld().getSettlement().addStorage(this);
    }

    public boolean accepts(Collection<Item> items) {
        int slotsOver = inventory.getMaxSize() - inventory.getSize();
        for (Item item : items) {
            if (inventory.getItems().containsKey(item.getType().getId())) { continue; }
            if (slotsOver > 0) {
                if (checkType(item.getType())) {
                    slotsOver--;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onDestroy() {
        Main.instance.getWorld().getSettlement().removeStorage(this);
    }

    public abstract boolean checkType(ItemType type);

    public Inventory getInventory() {
        return inventory;
    }
}
