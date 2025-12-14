package dev.codewizz.world;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public abstract class Entity extends GameObject {

    protected final Vector3 velocity;

    protected BehaviorTree<Entity> behaviour;
    protected ModelInstance instance;

    public Entity(String id) {
        super(id);

        velocity = new Vector3();
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public boolean isMoving() {
        return false;
    }

    public BehaviorTree<Entity> getBehaviorTree() {
        return behaviour;
    }

    public ModelInstance getInstance() {
        return instance;
    }
}
