package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.WUtils;

public class TreeStump extends Gatherable {

    private final ModelInstance instance;

    public TreeStump() {
        super("vxr:tree-stump");

        this.instance = new ModelInstance(Assets.findModel(getId()));
    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderObjectInstance(this, instance);
    }

    @Override
    public void gather(Hermit hermit) {
        destroy();
    }
}
