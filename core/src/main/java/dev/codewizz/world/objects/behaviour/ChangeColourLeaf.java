package dev.codewizz.world.objects.behaviour;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import dev.codewizz.world.Entity;

public class ChangeColourLeaf extends LeafTask<Entity> {

    private Color color;

    public ChangeColourLeaf(Color color) {
        this.color = color;
    }

    @Override
    public Status execute() {
        Entity entity = getObject();
        entity.getInstance().materials.get(0).set(new ColorAttribute(ColorAttribute.createDiffuse(color)));
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        ((ChangeColourLeaf) task).color = color;
        return task;
    }
}
