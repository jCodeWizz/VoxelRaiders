package dev.codewizz.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalShadowLight;
import com.badlogic.gdx.graphics.g3d.utils.DepthShaderProvider;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import dev.codewizz.gfx.shaders.ObjectShaderProvider;
import dev.codewizz.main.Main;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.World;
import dev.codewizz.world.objects.behaviour.pathfinding.NavGraph;
import dev.codewizz.world.voxel.Chunk;
import dev.codewizz.world.voxel.VoxelData;

import java.util.List;

public class Renderer {

    private final ModelBatch modelBatch;
    private final Camera camera;
    private final Environment environment;

    private final DirectionalShadowLight shadowLight;
    private final ModelBatch shadowBatch;
    private boolean shadow = false;

    private final Stage uiStage;
    private final Table root;

    public Renderer() {
        modelBatch = new ModelBatch(new ObjectShaderProvider());
        camera = new Camera();
        environment = new Environment();

        uiStage = new Stage(new ScreenViewport());
        root = new Table();
        root.setFillParent(true);
        uiStage.addActor(root);

        //environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        shadowLight = new DirectionalShadowLight(2048, 2048, 30f, 30f, 20f, 80f);
        shadowLight.set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f);
        environment.add(shadowLight);
        environment.shadowMap = shadowLight;

        shadowBatch = new ModelBatch(new DepthShaderProvider());

        camera.getPerspectiveCamera().position.set(10f, 10f, 10f);
        camera.getPerspectiveCamera().lookAt(0,0,0);
        camera.getPerspectiveCamera().update();
    }

    public void render(List<GameObject> objects, Chunk[][] chunks) {
        camera.update(Gdx.graphics.getDeltaTime());
        ScreenUtils.clear(0.533f, 0.768f, 0.925f, 1f, true);

        shadowLight.begin(new Vector3(0f, 11f, 0f), shadowLight.direction);
        shadowBatch.begin(shadowLight.getCamera());

        for (int i = 0; i < chunks.length; i++) {
            for (int j = 0; j < chunks[i].length; j++) {
                if (chunks[i][j].isDirty()) chunks[i][j].buildMesh();

                shadowBatch.render(chunks[i][j].getModelInstance());
            }
        }
        shadow = true;
        for (GameObject object : objects) {
            object.render(this);
        }
        shadow = false;
        shadowBatch.end();
        shadowLight.end();

        modelBatch.begin(camera.getPerspectiveCamera());

        for (int i = 0; i < chunks.length; i++) {
            for (int j = 0; j < chunks[i].length; j++) {
                if (chunks[i][j].isDirty()) chunks[i][j].buildMesh();

                modelBatch.render(chunks[i][j].getModelInstance(), environment);
            }
        }

        for (GameObject object : objects) {
            object.render(this);
        }

        renderDebug();

        modelBatch.end();

        uiStage.act(Gdx.graphics.getDeltaTime());
        uiStage.draw();
    }

    ModelInstance instance = new ModelInstance(new ModelBuilder().createLineGrid(
        NavGraph.SIZE,
        NavGraph.SIZE,
        1,
        1,
        new Material(ColorAttribute.createDiffuse(new Color(0.7f, 0.7f, 0.7f, 0.1f))),
        VertexAttributes.Usage.Position
    ));

    public void renderDebug() {
        instance.transform.setTranslation(0, 10.52f, 0);
        modelBatch.render(instance, environment);
    }

    public void renderObjectInstance(GameObject object, ModelInstance instance) {
        instance.transform.set(object.getPosition(), object.getRotation(), object.getScale());
        if (shadow) {
            shadowBatch.render(instance);
        } else {
            modelBatch.render(instance, environment);
        }
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
