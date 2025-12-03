package dev.codewizz.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;

public class Camera {

    private final PerspectiveCamera cam;

    public Camera() {
        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f, 10f, 10f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();
    }

    public PerspectiveCamera getPerspectiveCamera() {
        return cam;
    }
}
