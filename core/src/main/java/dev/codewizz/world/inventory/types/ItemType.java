package dev.codewizz.world.inventory.types;

import com.badlogic.gdx.graphics.g2d.Sprite;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.inventory.Item;
import java.util.HashMap;

public class ItemType {

    private static final HashMap<String, ItemType> REGISTRY = new HashMap<>();

    public static final ItemType BERRIES = register(new FoodType("vxr:berries", "Berries", 1));
    public static final ItemType LOG = register(new ItemType("vxr:log", "Log"));
    public static final ItemType PLANK = register(new ItemType("vxr:plank", "Plank"));
    public static final ItemType FIREWOOD = register(new ItemType("vxr:firewood", "Firewood"));

    private final String id;
    private final String name;
    private final Sprite sprite;

    public ItemType(String id, String name) {
        this.id = id;
        this.name = name;
        this.sprite = Assets.getSprite(id.split(":")[1]);
    }

    public static ItemType register(ItemType type) {
        REGISTRY.put(type.id, type);
        return type;
    }

    public static ItemType find(String id) {
        return REGISTRY.get(id);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
