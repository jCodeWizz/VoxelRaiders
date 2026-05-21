package dev.codewizz.world.inventory;

public class ItemType {

    public static final ItemType BERRIES = new ItemType("vxr:berries", "Berries");
    public static final ItemType LOG = new ItemType("vxr:log", "Log");

    public ItemType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private String id;
    private String name;

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
