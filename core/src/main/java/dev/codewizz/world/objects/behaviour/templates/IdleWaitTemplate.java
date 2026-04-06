package dev.codewizz.world.objects.behaviour.templates;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.leaf.Wait;
import com.badlogic.gdx.ai.utils.random.UniformFloatDistribution;
import dev.codewizz.world.Entity;
import dev.codewizz.world.objects.behaviour.TaskTemplate;

public class IdleWaitTemplate implements TaskTemplate {

    private final float time;
    private final float time2;

    public IdleWaitTemplate(float time) {
        this.time = time;
        this.time2 = time;
    }

    public IdleWaitTemplate(float time, float time2) {
        this.time = time;
        this.time2 = time2;
    }

    @Override
    public BehaviorTree<Entity> create(Entity entity) {
        if (time == time2) {
            return new BehaviorTree<>(
                new Wait<>(time),
                entity
            );
        } else {
            return new BehaviorTree<>(
                new Wait<>(new UniformFloatDistribution(time, time2)),
                entity
            );
        }
    }
}
