package dev.codewizz.world;

import java.util.ArrayList;
import java.util.List;

public class World {

    private final List<GameObject> objects = new ArrayList<>();

    public void addObject(GameObject object) {
        objects.add(object);
    }

    public void update(float dt) {
        for (GameObject object : objects) {
            object.update(dt);
        }
    }

    public List<GameObject> getObjectsToRender() {
        return objects;
    }
}
