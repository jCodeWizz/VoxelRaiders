package dev.codewizz.world;

import com.badlogic.gdx.graphics.g3d.utils.BaseAnimationController;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import dev.codewizz.gfx.Renderer;

public abstract class GameObject {

    private final Vector3 position;
    private final Quaternion rotation;
    private final Vector3 scale;

    private final String id;

    public GameObject(String id) {
        this.id = id;

        position = new Vector3();
        rotation = new Quaternion();
        scale = new Vector3(1, 1, 1);
    }

    public abstract void update(float dt);
    public abstract void render(Renderer renderer);

    public Vector3 getPosition() {
        return position;
    }

    public Quaternion getRotation() {
        return rotation;
    }

    public Vector3 getScale() {
        return scale;
    }

    public String getId() {
        return id;
    }
}
