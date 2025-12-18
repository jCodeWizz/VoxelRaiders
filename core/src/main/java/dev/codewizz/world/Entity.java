package dev.codewizz.world;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import dev.codewizz.world.objects.behaviour.pathfinding.NavAgent;

public abstract class Entity extends GameObject {

    protected final NavAgent agent;
    protected final Vector3 velocity;

    protected BehaviorTree<Entity> behaviour;
    protected ModelInstance instance;

    protected float speed = 2f;

    public Entity(String id) {
        super(id);

        velocity = new Vector3();
        agent = new NavAgent(this);
    }

    @Override
    public void update(float dt) {
        behaviour.step();
        agent.update(dt);
        getPosition().mulAdd(velocity, dt);
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public boolean isMoving() {
        return agent.getPath().size != 0;
    }

    public BehaviorTree<Entity> getBehaviorTree() {
        return behaviour;
    }

    public ModelInstance getInstance() {
        return instance;
    }

    public NavAgent getAgent() {
        return agent;
    }

    public float getSpeed() {
        return speed;
    }
}
