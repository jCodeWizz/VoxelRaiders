package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.WUtils;
import dev.codewizz.world.GameObject;

public class TreeStump extends GameObject {

    private final ModelInstance instance;

    public TreeStump(Vector3 pos) {
        super("vxr:tree-stump");

        this.instance = new ModelInstance(Assets.findModel(getId()));
        this.getPosition().set(pos);
        Main.instance.getWorld().getSettlement().addStation(this);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void onDestroy() {
        Main.instance.getWorld().getSettlement().removeStation(this);
    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderObjectInstance(this, instance);
    }
}
