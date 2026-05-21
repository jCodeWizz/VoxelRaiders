package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.GameObject;

public class Pebbles extends GameObject {

    private final ModelInstance instance;

    public Pebbles() {
        super("vxr:pebbles");

        this.instance = new ModelInstance(Assets.findModel(getId()));
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderObjectInstance(this, instance);
    }
}
