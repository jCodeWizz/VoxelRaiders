package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.WUtils;

public class Bush extends Gatherable {

    private final ModelInstance instance;

    public Bush() {
        super("vxr:bush");

        this.instance = new ModelInstance(Assets.findModel(getId()));
        this.getRotation().setFromAxis(Vector3.Y, WUtils.getRandom(0, 360));
        float scale = WUtils.RANDOM.nextFloat() * 0.5f + 1f;
        this.getScale().set(scale, scale, scale);
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
