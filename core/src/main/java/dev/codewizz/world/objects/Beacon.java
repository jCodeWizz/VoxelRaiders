package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.world.GameObject;

public class Beacon extends GameObject {

    private static final Material MATERIAL = new Material(ColorAttribute.createDiffuse(Color.WHITE));
    private static final Model MODEL = new ModelBuilder().createBox(1f, 1f, 1f, MATERIAL, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

    private final ModelInstance instance;

    public Beacon() {
        super("vxr:beacon");

        instance = new ModelInstance(MODEL);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderObjectInstance(this, instance);
    }
}
