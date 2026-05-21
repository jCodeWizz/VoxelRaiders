package dev.codewizz.world.inventory.types;

public class ItemType {

    public static final FoodType BERRIES = new FoodType("vxr:berries", "Berries", 1);
    public static final ItemType LOG = new ItemType("vxr:log", "Log");

    private final String id;
    private final String name;

    public ItemType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ItemType{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
    }
}
