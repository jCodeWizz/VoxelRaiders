package dev.codewizz.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;

public class Camera {

    private final PerspectiveCamera cam;
    private final CameraInputController inputController;

    public Camera() {
        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        inputController = new CameraInputController(cam);
        inputController.autoUpdate = true;
    }

    public void update(float dt) {
        inputController.update();
    }

    public PerspectiveCamera getPerspectiveCamera() {
        return cam;
    }

    public CameraInputController getInputController() {
        return inputController;
    }
}
