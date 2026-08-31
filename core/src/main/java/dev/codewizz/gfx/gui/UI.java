package dev.codewizz.gfx.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import dev.codewizz.gfx.gui.layers.Layer;

public class UI {

    public static int SCALE = 3;
    public static final Stage stage = new Stage();
    private static Layer layer;

    public static void render(SpriteBatch b) {
        layer.update(Gdx.graphics.getDeltaTime());
        layer.render(b);
    }

    public static void openLayer(Layer layer) {
        UI.layer = layer;
        UI.layer.open(stage);
    }

    public static Layer getLayer() {
        return layer;
    }
}
