package dev.codewizz.world.objects.behaviour.templates;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.branch.Parallel;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.badlogic.gdx.ai.btree.leaf.Wait;
import com.badlogic.gdx.math.Vector3;
import dev.codewizz.world.Entity;
import dev.codewizz.world.objects.behaviour.TaskTemplate;
import dev.codewizz.world.objects.behaviour.leaves.AnimationLeaf;
import dev.codewizz.world.objects.behaviour.leaves.ParticleLeaf;

public class GrazeTemplate implements TaskTemplate {

    @Override
    public BehaviorTree<Entity> create(Entity entity) {
        return new BehaviorTree<>(
            new Sequence<>(
                new AnimationLeaf("graze"),
                new Wait<>(0.5f),
                new ParticleLeaf("particles/grass.pfx", new Vector3(entity.getPosition()).add(entity.getRotation().transform(new Vector3(0, 0, -1)))),
                new Wait<>(1.5f)
            ),
            entity
        );
    }

    @Override
    public boolean canTake(Entity entity) {
        return true;
    }
}
