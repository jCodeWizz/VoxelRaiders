package dev.codewizz.world;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.badlogic.gdx.ai.btree.decorator.Repeat;
import com.badlogic.gdx.ai.btree.leaf.Wait;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Queue;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.main.Main;
import dev.codewizz.world.objects.behaviour.TaskTemplate;
import dev.codewizz.world.objects.behaviour.leaves.ExecuteTaskLeaf;
import dev.codewizz.world.objects.behaviour.leaves.GetTaskLeaf;
import dev.codewizz.world.objects.behaviour.leaves.HasTaskLeaf;
import dev.codewizz.world.objects.behaviour.pathfinding.NavAgent;

public abstract class Entity extends GameObject {

    protected final NavAgent agent;
    protected final Vector3 velocity;
    protected float speed = 2f;

    protected BehaviorTree<Entity> behaviour;
    private final Queue<BehaviorTree<Entity>> tasks = new Queue<>();
    private BehaviorTree<Entity> currentTask;

    protected ModelInstance instance;

    public Entity(String id) {
        super(id);

        velocity = new Vector3();
        agent = new NavAgent(this);

        behaviour = new BehaviorTree<>(
            new Repeat<>(
                new Sequence<>(
                    new Sequence<>(
                        new HasTaskLeaf(),
                        new ExecuteTaskLeaf()
                    ),
                    new Wait<>(2f)
                )
            ),
            this
        );
    }

    public abstract TaskTemplate findNewTask();

    @Override
    public void update(float dt) {
        separate();
        behaviour.step();
        agent.update(dt);
        getPosition().mulAdd(velocity, dt);
        updateRotationFromVelocity();
    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderObjectInstance(this, instance);
    }

    private void separate() {
        for (GameObject other : Main.instance.world.getObjectsToRender()) {
            if (other == this || !(other instanceof Entity)) continue;

            Vector3 otherPos = other.getPosition();

            Vector3 diff = new Vector3(getPosition()).sub(otherPos);
            float distance = diff.len();

            float minDistance = 1;

            if (distance < minDistance && distance > 0.0001f) {
                diff.nor();
                diff.y = 0;

                float overlap = minDistance - distance;
                getPosition().add(diff.scl(overlap * 0.5f));
                other.getPosition().sub(diff.scl(overlap * 0.5f));
            }
        }
    }

    public void updateRotationFromVelocity() {
        if (velocity.isZero(0.0001f)) return;

        Vector3 dir = new Vector3(velocity.x, 0f, velocity.z).nor();
        float yaw = MathUtils.atan2(dir.x, dir.z) * MathUtils.radiansToDegrees - 180;
        getRotation().set(Vector3.Y, yaw);
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public boolean isMoving() {
        return agent.getPath().isEmpty();
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

    public boolean hasTask() {
        return currentTask != null;
    }

    public Queue<BehaviorTree<Entity>> getTasks() {
        return tasks;
    }

    public BehaviorTree<Entity> getCurrentTask() {
        return currentTask;
    }

    public void finishCurrentTask() {
        this.currentTask = null;
    }

    public void addTask(BehaviorTree<Entity> task) {
        this.tasks.addFirst(task);
    }

    public void setCurrentTask() {
        this.currentTask = tasks.removeFirst();
    }
}
