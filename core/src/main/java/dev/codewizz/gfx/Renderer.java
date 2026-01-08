package dev.codewizz.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.voxel.Chunk;

import java.util.List;

public class Renderer {

    private final ModelBatch modelBatch;
    private final Camera camera;
    private final Environment environment;

    private final Stage uiStage;
    private final Table root;

    public Renderer() {
        modelBatch = new ModelBatch();
        camera = new Camera();
        environment = new Environment();

        uiStage = new Stage(new ScreenViewport());
        root = new Table();
        root.setFillParent(true);
        uiStage.addActor(root);


            environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        camera.getPerspectiveCamera().position.set(10f, 10f, 10f);
        camera.getPerspectiveCamera().lookAt(0,0,0);
        camera.getPerspectiveCamera().update();
    }

    public void render(List<GameObject> objects, List<Chunk> chunks) {
        camera.update(Gdx.graphics.getDeltaTime());
        ScreenUtils.clear(0.533f, 0.768f, 0.925f, 1f, true);

        modelBatch.begin(camera.getPerspectiveCamera());

        for (Chunk chunk : chunks) {
            if (chunk.isDirty()) chunk.buildMesh();

            modelBatch.render(chunk.getModelInstance(), environment);
        }

        for (GameObject object : objects) {
            object.render(this);
        }

        modelBatch.end();

        uiStage.act(Gdx.graphics.getDeltaTime());
        uiStage.draw();
    }

    public void renderObjectInstance(GameObject object, ModelInstance instance) {
        instance.transform.set(object.getPosition(), object.getRotation(), object.getScale());
        modelBatch.render(instance, environment);
    }

    public void resize(int width, int height) {
        uiStage.getViewport().update(width, height, true);
    }

    public void dispose() {
        modelBatch.dispose();
        uiStage.dispose();
    }

    public Stage getUiStage() {
        return uiStage;
    }

    public Camera getCamera() {
        return camera;
    }
}
