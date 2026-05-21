package dev.codewizz.world.inventory;

import dev.codewizz.world.inventory.types.ItemType;

public class Item {

    private int size;
    private ItemType type;

    public Item(ItemType type, int size) {
        this.size = size;
        this.type = type;
    }

    public Item(ItemType type) {
        this(type, 1);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ItemType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Item{" + "size=" + size + ", type=" + type + '}';
    }
}
