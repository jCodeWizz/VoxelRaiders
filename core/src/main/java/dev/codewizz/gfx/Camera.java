package dev.codewizz.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.collision.Ray;

public class Camera implements InputProcessor {

    private final PerspectiveCamera cam;

    private final Vector3 pivot = new Vector3(0, 0, 0);

    private float distance = 30f;
    private float yaw = 45f;
    private float pitch = 45f;

    private final float moveSpeed = 25f;
    private final float rotateSpeed = 0.3f;
    private final float minDistance = 20f;
    private final float maxDistance = 50;

    private boolean rotating = false;
    private final Vector2 lastMouse = new Vector2();

    private final Plane groundPlane = new Plane(new Vector3(0, 1, 0), 0);
    private final Vector3 tmpV = new Vector3();

    public Camera() {
        cam = new PerspectiveCamera(
            67,
            Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight()
        );

        cam.near = 0.1f;
        cam.far = 1000f;

        updateCamera();
    }

    public void update(float dt) {

        float speed = moveSpeed * dt;

        Vector3 forward = new Vector3(cam.direction);
        forward.y = 0;
        forward.nor();

        Vector3 right = new Vector3(forward).crs(Vector3.Y).nor();

        if (Gdx.input.isKeyPressed(Input.Keys.W)) pivot.add(forward.x * speed, 0, forward.z * speed);
        if (Gdx.input.isKeyPressed(Input.Keys.S)) pivot.sub(forward.x * speed, 0, forward.z * speed);
        if (Gdx.input.isKeyPressed(Input.Keys.A)) pivot.sub(right.x * speed, 0, right.z * speed);
        if (Gdx.input.isKeyPressed(Input.Keys.D)) pivot.add(right.x * speed, 0, right.z * speed);

        updateCamera();
    }

    private void updateCamera() {

        float yawRad = yaw * MathUtils.degreesToRadians;
        float pitchRad = pitch * MathUtils.degreesToRadians;

        float x = distance * MathUtils.cos(pitchRad) * MathUtils.cos(yawRad);
        float y = distance * MathUtils.sin(pitchRad);
        float z = distance * MathUtils.cos(pitchRad) * MathUtils.sin(yawRad);

        cam.position.set(
            pivot.x + x,
            pivot.y + y,
            pivot.z + z
        );

        cam.lookAt(pivot);
        cam.up.set(Vector3.Y);
        cam.update();
    }

    private Vector3 getMouseWorldOnGround(int screenX, int screenY) {
        Ray ray = cam.getPickRay(screenX, screenY);
        Intersector.intersectRayPlane(ray, groundPlane, tmpV);
        return tmpV;
    }

    // ---------------- INPUT ----------------

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (button == Input.Buttons.MIDDLE) {
            rotating = true;
            lastMouse.set(screenX, screenY);
            return true;
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (button == Input.Buttons.MIDDLE) {
            rotating = false;
            return true;
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        if (!rotating) return false;

        float dx = screenX - lastMouse.x;
        float dy = screenY - lastMouse.y;

        lastMouse.set(screenX, screenY);

        yaw += dx * rotateSpeed;
        pitch += dy * rotateSpeed;

        pitch = MathUtils.clamp(pitch, 20f, 80f);

        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {

        Vector3 mouseWorld = getMouseWorldOnGround(
            Gdx.input.getX(),
            Gdx.input.getY()
        );

        float zoomFactor = 1f + amountY * 0.1f;

        float newDistance = distance * zoomFactor;
        newDistance = MathUtils.clamp(newDistance, minDistance, maxDistance);

        pivot.lerp(mouseWorld, 0.25f);

        distance = newDistance;

        return true;
    }

    // --------------- UNUSED ---------------

    @Override public boolean keyDown(int keycode) { return false; }
    @Override public boolean keyUp(int keycode) { return false; }
    @Override public boolean keyTyped(char character) { return false; }
    @Override public boolean touchCancelled(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean mouseMoved(int screenX, int screenY) { return false; }

    // --------------- GETTER ---------------

    public PerspectiveCamera getPerspectiveCamera() {
        return cam;
    }
}
