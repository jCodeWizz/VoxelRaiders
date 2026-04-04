package dev.codewizz.world.objects;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.decorator.Repeat;
import com.badlogic.gdx.ai.btree.leaf.Wait;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.Entity;

public class Hermit extends Entity {

    private static final Material MATERIAL = new Material(ColorAttribute.createDiffuse(new Color(0.1f, 0.93f, 0.95f, 1.0f)));
    private static final Model MODEL = new ModelBuilder().createBox(0.8f, 1.5f, 0.8f, MATERIAL,VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

    public Hermit() {
        super("vxr:hermit");

        instance = new ModelInstance(MODEL);
        getSize().set(0.8f, 1.5f, 0.8f);

        behaviour = new BehaviorTree<>(
            new Repeat<>(
                new Wait<>(5f)
            ),
            this
        );
    }
}
