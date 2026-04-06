package dev.codewizz.world.objects;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.badlogic.gdx.ai.btree.decorator.Repeat;
import com.badlogic.gdx.ai.btree.leaf.Wait;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.Queue;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.Logger;
import dev.codewizz.world.Entity;
import dev.codewizz.world.objects.behaviour.TaskTemplate;
import dev.codewizz.world.objects.behaviour.leaves.ExecuteTaskLeaf;
import dev.codewizz.world.objects.behaviour.leaves.GetTaskLeaf;
import dev.codewizz.world.objects.behaviour.leaves.HasTaskLeaf;
import dev.codewizz.world.objects.behaviour.pathfinding.NavAgent;
import dev.codewizz.world.objects.behaviour.templates.IdleWaitTemplate;
import dev.codewizz.world.objects.behaviour.templates.MoveToTemplate;

public class Hermit extends Entity {

    private static final IdleWaitTemplate WAIT = new IdleWaitTemplate(2f, 5f);

    private static final Material MATERIAL = new Material(ColorAttribute.createDiffuse(new Color(0.1f, 0.93f, 0.95f, 1.0f)));
    private static final Model MODEL = new ModelBuilder().createBox(0.8f, 1.5f, 0.8f, MATERIAL,VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

    public Hermit() {
        super("vxr:hermit");

        instance = new ModelInstance(MODEL);
        getSize().set(0.8f, 1.5f, 0.8f);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }

    @Override
    public TaskTemplate findNewTask() {

        TaskTemplate next = null;
        for (TaskTemplate t : Main.instance.world.getSettlement().getTasks()) {
            if (t.canTake(this)) {
                next = t;
                break;
            }
        }

        if (next != null) {
            Main.instance.world.getSettlement().getTasks().removeValue(next, false);
            return next;
        }

        return WAIT;
    }
}
