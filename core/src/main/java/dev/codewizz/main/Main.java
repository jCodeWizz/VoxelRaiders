package dev.codewizz.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import dev.codewizz.gfx.Camera;

public class Main extends ApplicationAdapter {

    public Camera camera;

    public ModelBatch modelBatch;

    @Override
    public void create () {
        camera = new Camera();
    }

    @Override
    public void render () {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f, true);

        modelBatch.begin(camera.getPerspectiveCamera());
        //TODO: bulk render
        modelBatch.end();
    }

    @Override
    public void dispose () {
        modelBatch.dispose();
    }
}
