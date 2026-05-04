package dev.codewizz.gfx.ui.panels;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.codewizz.gfx.ui.UI;

public abstract class Panel extends Table {

    protected Table content;

    private final Sprite background;
    private final Stack stack;

    public Panel(Sprite background) {
        this.background = background;
        this.stack = new Stack();

        Table backGroundTable = new Table();
        backGroundTable.add(new Image(background)).size(background.getWidth() * UI.SCALE, background.getHeight() * UI.SCALE);

        stack.add(backGroundTable);

        setup();

        stack.add(content);
    }

    abstract void setup();

    public void open() {

    }

    public void close() {

    }
}
