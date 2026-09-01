package dev.codewizz.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import dev.codewizz.gfx.Camera;
import dev.codewizz.gfx.gui.UI;
import dev.codewizz.gfx.gui.layers.GameLayer;
import dev.codewizz.gfx.gui.menus.ConsoleMenu;
import dev.codewizz.input.result.PickAreaListener;
import dev.codewizz.input.result.PickChunkResult;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Logger;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.World;
import dev.codewizz.world.objects.Gatherable;
import dev.codewizz.world.objects.SmallPile;
import dev.codewizz.world.objects.behaviour.pathfinding.NavAgent;
import dev.codewizz.world.objects.behaviour.pathfinding.NavCell;
import dev.codewizz.world.objects.behaviour.templates.GatherTemplate;
import dev.codewizz.world.objects.behaviour.templates.MoveToTemplate;

import java.util.List;

public class KeyInput implements InputProcessor {

    private final Camera camera;
    private final World world;

    public KeyInput(Camera camera, World world) {
        this.camera = camera;
        this.world = world;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.NUM_0) {
            GameLayer layer = (GameLayer) UI.getLayer();
            layer.speed0.setChecked(true);
            Main.gameSpeed = 0;
        }

        if (keycode == Input.Keys.NUM_1) {
            GameLayer layer = (GameLayer) UI.getLayer();
            layer.speed1.setChecked(true);
            Main.gameSpeed = 1;
        }

        if (keycode == Input.Keys.NUM_2) {
            GameLayer layer = (GameLayer) UI.getLayer();
            layer.speed2.setChecked(true);
            Main.gameSpeed = 3;
        }

        if (keycode == Input.Keys.NUM_3) {
            GameLayer layer = (GameLayer) UI.getLayer();
            layer.speed3.setChecked(true);
            Main.gameSpeed = 5;
        }

        if (keycode == Input.Keys.ESCAPE) {
            UI.getLayer().closeMenus();
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                Gdx.app.exit();
            }
        }

        if (keycode == Input.Keys.F1) {
            UI.getLayer().openMenu(ConsoleMenu.ID);
        }

        if (keycode == Input.Keys.H) {
            if (world.getSettlement() != null) {
                MouseInput.pickAreaListener = (min, max) -> {

                    List<GameObject> objects = world.getObjectsWithinBounds(min, max);

                    for (GameObject object : objects) {
                        if (object instanceof Gatherable)  {
                            world.getSettlement().addTask(new GatherTemplate((Gatherable) object));
                        }
                    }
                };
            }
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
