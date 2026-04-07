package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import dev.codewizz.gfx.Renderer;

public class Tree extends Gatherable {

    private final static Material MATERIAL = new Material(ColorAttribute.createDiffuse(Color.BROWN));
    private final static Model MODEL = new ModelBuilder().createBox(1f, 0.5f, 1f, MATERIAL, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

    private final ModelInstance instance;

    public Tree() {
        super("vxr:tree");

        this.instance = new ModelInstance(MODEL);
    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderObjectInstance(this, instance);
    }

    @Override
    public void gather() {
        destroy();
    }
}
