package dev.codewizz.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import dev.codewizz.gfx.Camera;
import dev.codewizz.input.result.PickChunkResult;
import dev.codewizz.utils.Logger;
import dev.codewizz.world.World;
import dev.codewizz.world.objects.behaviour.pathfinding.NavAgent;
import dev.codewizz.world.objects.behaviour.pathfinding.NavCell;
import dev.codewizz.world.objects.behaviour.templates.MoveToTemplate;

public class KeyInput implements InputProcessor {

    private final Camera camera;
    private final World world;

    public KeyInput(Camera camera, World world) {
        this.camera = camera;
        this.world = world;
    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == Input.Keys.R) {
            if (world.getSettlement() != null) {
                PickChunkResult result = MouseInput.pickChunk(camera, world, Gdx.input.getX(), Gdx.input.getY());
                if (result.getChunk() != null) {

                    NavCell cell = NavAgent.graph.getCell(result.getIntersection());
                    world.getSettlement().addTask(new MoveToTemplate(cell));

                    return true;
                }
            }
        }

        if (keycode == Input.Keys.G) {
            world.getObjectsToRender().clear();
            world.generateFeatures();
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
