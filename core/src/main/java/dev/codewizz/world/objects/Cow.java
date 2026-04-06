package dev.codewizz.world.objects;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.branch.RandomSelector;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.badlogic.gdx.ai.btree.decorator.Repeat;
import com.badlogic.gdx.ai.btree.leaf.Wait;
import com.badlogic.gdx.ai.utils.random.UniformFloatDistribution;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.Entity;
import dev.codewizz.world.objects.behaviour.TaskTemplate;
import dev.codewizz.world.objects.behaviour.pathfinding.NavAgent;
import dev.codewizz.world.objects.behaviour.templates.MoveToTemplate;

public class Cow extends Entity {

    public Cow() {
        super("vxr:cow");

        instance = new ModelInstance(Assets.findModel(getId()));
    }

    @Override
    public TaskTemplate findNewTask() {
        return new MoveToTemplate(NavAgent.graph.getCell(10, 10));
    }
}
