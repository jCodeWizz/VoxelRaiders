package dev.codewizz.gfx;

import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.utils.ScreenUtils;
import dev.codewizz.world.GameObject;
import java.util.List;

public class Renderer {

    private final ModelBatch modelBatch;
    private final Camera camera;

    public Renderer() {
        modelBatch = new ModelBatch();
        camera = new Camera();
    }

    public void render(List<GameObject> objects) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f, true);

        modelBatch.begin(camera.getPerspectiveCamera());

        for (GameObject object : objects) {
            object.render(this);
        }

        modelBatch.end();
    }

    public void renderObjectInstance(GameObject object, ModelInstance instance) {
        instance.transform.set(object.getPosition(), object.getRotation(), object.getScale());
        modelBatch.render(instance);
    }

    public void dispose() {
        modelBatch.dispose();
    }
}
