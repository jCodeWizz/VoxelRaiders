package dev.codewizz.world;

import java.util.ArrayList;
import java.util.List;

public class World {

    private final List<GameObject> objects = new ArrayList<>();

    public boolean addObject(GameObject object) {
        return objects.add(object);
    }

    public List<GameObject> getObjectsToRender() {
        return objects;
    }
}
