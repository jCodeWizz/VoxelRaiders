package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.world.GameObject;

public class Terrain extends GameObject {

    private final ModelInstance instance;

    public Terrain() {
        super("terrain");

        instance = new ModelInstance(new ModelBuilder().createBox(
            10f, 0.1f, 10f, new Material(ColorAttribute.createDiffuse(new Color(0.235f, 0.702f, 0.443f, 1.0f))),
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
