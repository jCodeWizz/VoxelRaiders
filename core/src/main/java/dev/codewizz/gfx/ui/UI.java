package dev.codewizz.gfx.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class UI {

    public static final int SCALE = 2;
    public static final Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

    private final Stage stage;
    private final Table root;

    public UI() {
        stage = new Stage(new ExtendViewport(1920, 1080));
        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        root.add(new HUD()).expand().fillX().bottom();
    }

    public Stage getStage() {
        return stage;
    }
}
