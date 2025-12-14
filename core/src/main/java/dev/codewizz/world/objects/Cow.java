package dev.codewizz.world.objects;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.branch.RandomSelector;
import com.badlogic.gdx.ai.btree.branch.Selector;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.badlogic.gdx.ai.btree.decorator.Repeat;
import com.badlogic.gdx.ai.btree.leaf.Wait;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.world.Entity;
import dev.codewizz.world.objects.behaviour.ChangeColourLeaf;

public class Cow extends Entity {

    public Cow() {
        super("cow");

        instance = new ModelInstance(new ModelBuilder().createBox(
            1f, 1f, 1f, new Material(ColorAttribute.createDiffuse(Color.BLACK)),
            VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal)
        );

        behaviour = new BehaviorTree<>(
            new Repeat<>(
                new RandomSelector<>(
                    new Sequence<>(
                        new Wait<>(5f),
                        new ChangeColourLeaf(Color.RED)
                    ),
                    new Sequence<>(
                        new Wait<>(5f),
                        new ChangeColourLeaf(Color.YELLOW)
                    ),
                    new Sequence<>(
                        new Wait<>(5f),
                        new ChangeColourLeaf(Color.GREEN)
                    )
                )
            ),
            this
        );
    }

    @Override
    public void update(float dt) {
        behaviour.step();
    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderObjectInstance(this, instance);
    }
}
