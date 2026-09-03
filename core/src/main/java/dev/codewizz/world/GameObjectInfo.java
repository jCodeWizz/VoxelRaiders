package dev.codewizz.world;

public class GameObjectInfo {

    private final String id;
    private final String name;
    private final String description;
    private final Class<? extends GameObject> clazz;

    public GameObjectInfo(String id, String name, String description, Class<? extends GameObject> clazz) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.clazz = clazz;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Class<? extends GameObject> getClazz() {
        return clazz;
    }
}
