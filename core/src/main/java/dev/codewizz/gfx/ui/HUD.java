package dev.codewizz.gfx.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import dev.codewizz.utils.Assets;

public class HUD extends Table {

    public HUD() {
        setup();
    }

    private void setup() {



        Table content = new Table();
        Table left = new Table();
        Table right = new Table();

        content.add(left).expand().fillX().left();
        content.add(centerTable()).expand().width(146 * UI.SCALE).center();
        content.add(right).expand().fillX().right();

        Stack stack = new Stack();
        stack.add(backgroundTable());
        stack.add(content);

        add(stack).expandX().fillX().height(20 * UI.SCALE).bottom();
    }

    private Table backgroundTable() {
        Table background = new Table();

        Image board = new Image(new SpriteDrawable(Assets.findSprite("icon-board")));
        Image extension = new Image(new SpriteDrawable(Assets.findSprite("icon-board-extension")));
        Image extension2 = new Image(new SpriteDrawable(Assets.findSprite("icon-board-extension")));

        background.add(extension).expandX().fillX().height(11 * UI.SCALE).bottom();
        background.add(board).width(146 * UI.SCALE).height(20 * UI.SCALE).bottom();
        background.add(extension2).expandX().fillX().height(11 * UI.SCALE).bottom();

        return background;
    }

    private Table centerTable() {
        Table center = new Table();
        center.add(new TextButton("Icon1", UI.skin)).size(22 * UI.SCALE, 24 * UI.SCALE).padBottom(16 * UI.SCALE);
        center.add(new TextButton("Icon2", UI.skin)).size(22 * UI.SCALE, 24 * UI.SCALE).padBottom(16 * UI.SCALE).padLeft(6 * UI.SCALE).padRight(6 * UI.SCALE);
        center.add(new TextButton("Icon3", UI.skin)).size(22 * UI.SCALE, 24 * UI.SCALE).padBottom(16 * UI.SCALE);
        center.add(new TextButton("Icon4", UI.skin)).size(22 * UI.SCALE, 24 * UI.SCALE).padBottom(16 * UI.SCALE).padLeft(6 * UI.SCALE).padRight(6 * UI.SCALE);
        center.add(new TextButton("Icon5", UI.skin)).size(22 * UI.SCALE, 24 * UI.SCALE).padBottom(16 * UI.SCALE);

        return center;
    }
}
