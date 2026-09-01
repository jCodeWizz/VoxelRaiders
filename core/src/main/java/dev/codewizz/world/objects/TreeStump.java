package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.GameObject;

public class TreeStump extends GameObject {
    public static final String ID = "vxr:tree-stump";

    private final ModelInstance instance;

    public TreeStump() {
        super(ID);

        this.name = "Tree Stump";
        this.description = "Crude crafting with logs";

        this.instance = new ModelInstance(Assets.findModel(getId()));
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
