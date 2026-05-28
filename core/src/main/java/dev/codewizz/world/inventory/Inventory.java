package dev.codewizz.world.inventory;

import dev.codewizz.world.inventory.types.ItemType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inventory {

    private HashMap<String, Item> items = new HashMap();
    private int maxSize;

    public Inventory() {
        this(-1);
    }

    public Inventory(int maxSize) {
        this.maxSize = maxSize;
    }

    public boolean isFull(Item item) {
        if (items.size() < maxSize) return false;
        else {
            return !items.containsKey(item.getType().getId());
        }
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

        if (items.get(item.getType().getId()).getSize() == item.getSize()) {
            items.remove(item.getType().getId());
            return true;
        } else if (items.get(item.getType().getId()).getSize() > item.getSize()) {
            items.get(item.getType().getId()).setSize(items.get(item.getType().getId()).getSize() - item.getSize());
            return true;
        } else {
            return false;
        }
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

    public int getItemSize(ItemType type) {
        if (items.containsKey(type.getId())) {
            return items.get(type.getId()).getSize();
        }
        return 0;
    }

    public HashMap<String, Item> getItems() {
        return items;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getSize() {
        return items.size();
    }

    @Override
    public String toString() {
        return "Inventory{" + "items=" + items + ", maxSize=" + maxSize + '}';
    }
}
