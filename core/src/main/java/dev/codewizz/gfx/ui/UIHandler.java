package dev.codewizz.gfx.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class UIHandler {

    private final Stage stage;
    private final Table root;
    private final Skin skin;

    public UIHandler() {
        stage = new Stage(new ScreenViewport());
        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
    }

    public Stage getStage() {
        return stage;
    }
}
