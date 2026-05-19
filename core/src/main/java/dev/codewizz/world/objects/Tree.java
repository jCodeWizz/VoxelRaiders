package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.WUtils;

public class Tree extends Gatherable {

    private final ModelInstance instance;

    public Tree() {
        super("vxr:tree");

        this.instance = new ModelInstance(Assets.findModel(getId()));
        this.getRotation().setFromAxis(Vector3.Y, WUtils.getRandom(0, 360));
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
