package dev.codewizz.world.objects.behaviour.leaves;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.Vector3;
import dev.codewizz.main.Main;
import dev.codewizz.world.Entity;

public class ParticleLeaf extends LeafTask<Entity> {

    private String effect;
    private Vector3 position;

    public ParticleLeaf(String effect, Vector3 position) {
        this.effect = effect;
        this.position = position;
    }

    @Override
    public Status execute() {
        Main.instance.getRenderer().getParticles().spawn(effect, position);
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        ((ParticleLeaf) task).effect = effect;
        ((ParticleLeaf) task).position = position;
        return task;
    }
}
