package dev.codewizz.gfx.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import dev.codewizz.gfx.ui.panels.Panel;

import java.util.ArrayList;

public class UI {

    public static final float SCALE = 2f;
    public static final Skin skin = new Skin(Gdx.files.internal("ui/vxrskin.json"));

    private final Stage stage;
    private final Table root;

    private final ArrayList<Panel> panels = new ArrayList<>();

    public UI() {
        stage = new Stage(new ScreenViewport());
        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        root.add(new HUD()).expand().fillX().bottom();

        skin.getAtlas().getTextures().forEach(texture -> {
            texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        });
    }

    public void update() {
        for (Panel panel : panels) {
            panel.update();
        }
    }

    public void open(Panel panel) {
        panel.open();
        panel.setFillParent(false);
        stage.addActor(panel);
        panels.add(panel);
    }

    public void close(Panel panel) {
        panels.remove(panel);
        panel.remove();
    }

    public Stage getStage() {
        return stage;
    }
}
