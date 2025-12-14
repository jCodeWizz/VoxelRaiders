package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.world.GameObject;

public class Prop extends GameObject {

    private final ModelInstance instance;

    public Prop() {
        instance = new ModelInstance(new ModelBuilder().createBox(
            1f, 1f, 1f, new Material(ColorAttribute.createDiffuse(Color.GREEN)),
            VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal)
        );
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderObjectInstance(this, instance);
    }
}
