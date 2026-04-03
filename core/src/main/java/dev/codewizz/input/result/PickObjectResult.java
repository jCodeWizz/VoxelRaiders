package dev.codewizz.input.result;

import com.badlogic.gdx.math.Vector3;
import dev.codewizz.world.GameObject;

public class PickObjectResult {

    private final Vector3 intersection;
    private GameObject object;
    private float distance;

    public PickObjectResult() {
        this.intersection = new Vector3();
        this.object = null;
        this.distance = Float.MAX_VALUE;
    }

    public Vector3 getIntersection() {
        return intersection;
    }

    public GameObject getObject() {
        return object;
    }

    public void setObject(GameObject object) {
        this.object = object;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
