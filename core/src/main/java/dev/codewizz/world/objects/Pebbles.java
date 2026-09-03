package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.GameObjectInfo;

public class Pebbles extends GameObject {
    public static final GameObjectInfo INFO = new GameObjectInfo("vxr:pebbles", "Pebbles", "Don't step on them with bare feet...", Pebbles.class);

    private final ModelInstance instance;

    public Pebbles() {
        super(INFO.getId());

        this.name = "Pebbles";
        this.description = "Don't step on them with bare feet...";

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
