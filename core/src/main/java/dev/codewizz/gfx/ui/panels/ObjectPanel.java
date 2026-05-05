package dev.codewizz.gfx.ui.panels;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import dev.codewizz.gfx.ui.UI;
import dev.codewizz.utils.Assets;

public class ObjectPanel extends Panel{

    public ObjectPanel() {
        super(Assets.findSprite("path-menu"));
    }

    @Override
    void setup() {
        Table top = new Table();
        Table categories = new Table();
        Table list = new Table();
        Table info = new Table();

        content.add(top).expand().fillX().height(25 * UI.SCALE).row();
        content.add(categories).expand().fillX().height(23 * UI.SCALE).row();
        content.add(list).expand().fillX().height(255 * UI.SCALE).row();
        content.add(info).expand().fillX().height(215 * UI.SCALE).row();

        top.add(new Label("Objects", UI.skin)).expand().left().padLeft(4 *  UI.SCALE);
        ImageButton closeButton = closeIcon();
        top.add(closeButton).size(22 * UI.SCALE, 24 * UI.SCALE).expand().right();
        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                close();
            }
        });
    }

    private ImageButton closeIcon() {
        ImageButton.ImageButtonStyle style =
            new ImageButton.ImageButtonStyle(UI.skin.get("stretch", ImageButton.ImageButtonStyle.class));

        style.imageUp = new SpriteDrawable(Assets.findSprite("close-icon"));

        ImageButton button = new ImageButton(style);
        button.getImageCell().expand().fill();

        return button;
    }
}
