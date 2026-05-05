package dev.codewizz.gfx.ui.panels;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.codewizz.gfx.ui.UI;
import dev.codewizz.main.Main;

public abstract class Panel extends Table {

    protected Table content;

    private final Stack stack;

    public Panel(Sprite background) {
        this.stack = new Stack();
        this.content = new Table();

        float width = background.getWidth() * UI.SCALE;
        float height = background.getHeight() * UI.SCALE;

        setSize(width, height);

        Table backGroundTable = new Table();
        backGroundTable.add(new Image(background)).expand().fill();

        stack.add(backGroundTable);

        setup();

        stack.add(content);
        add(stack).size(width, height);
    }

    abstract void setup();

    public void open() {

    }

    public void close() {
        Main.instance.getRenderer().getUI().close(this);
    }
}
