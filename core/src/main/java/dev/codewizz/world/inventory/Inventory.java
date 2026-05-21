package dev.codewizz.world.inventory;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {

    private HashMap<String, Item> items = new HashMap();
    private int maxSize;

    public Inventory() {
        this(-1);
    }

    public Inventory(int maxSize) {
        this.maxSize = maxSize;
    }

    public boolean containsItem(Item item) {
        if (items.containsKey(item.getType().getId())) {
            if (items.get(item.getType().getId()).getSize() >= item.getSize()) {
                return true;
            }
        }
        return false;
    }

    public boolean removeItem(Item item) {
        if (!containsItem(item)) { return false;}

        items.get(item.getType().getId()).setSize(items.get(item.getType().getId()).getSize() - item.getSize());

        return true;
    }

    public boolean addItem(Item item) {
        if (items.containsKey(item.getType().getId())) {
            items.get(item.getType().getId()).setSize(items.get(item.getType().getId()).getSize() + item.getSize());
            return true;
        }

        if (items.size() == maxSize) { return false; }
        items.put(item.getType().getId(), item);
        return true;
    }

    @Override
    public String toString() {
        return "Inventory{" + "items=" + items + ", maxSize=" + maxSize + '}';
    }
}
