package dev.codewizz.gfx.gui.menus;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import dev.codewizz.gfx.gui.UI;
import dev.codewizz.gfx.gui.elements.UIIconButton;
import dev.codewizz.gfx.gui.elements.UIImageButton;
import dev.codewizz.input.MouseInput;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.objects.Cow;
import dev.codewizz.world.objects.IBuy;

import java.util.ArrayList;
import java.util.List;

public class ObjectMenu extends Menu {

    public static final String ID = "object";

    public static List<GameObject> objects = new ArrayList<>();

    static {
        objects.add(new Cow());
        objects.add(new Cow());
        objects.add(new Cow());
        objects.add(new Cow());
        objects.add(new Cow());
        objects.add(new Cow());
        objects.add(new Cow());
        objects.add(new Cow());
        objects.add(new Cow());
        objects.add(new Cow());
        objects.add(new Cow());
    }

    private Table scrollTable;
    private ScrollPane scrollPane;

    @Override
    protected void setup() {
        Table main = new Table();
        main.setBackground(new SpriteDrawable(Assets.getSprite("object-menu")));

        Table top = new Table();
        Table categories = new Table();

        scrollTable = new Table();
        scrollPane = new ScrollPane(scrollTable);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setOverscroll(false, true);
        scrollPane.setScrollbarsVisible(true);
        fillScrollTable(objects);

        Table view = new Table();

        base.add(main).size(148 * UI.SCALE, 328 * UI.SCALE).expand().left().padLeft(10 * UI.SCALE);

        main.add(top).expand().size(148 * UI.SCALE, 25*UI.SCALE).row();
        main.add(categories).expand().size(148 * UI.SCALE, 24*UI.SCALE).row();
        main.add(scrollPane).expand().size(148 * UI.SCALE, 146*UI.SCALE).row();
        main.add(view).expand().size(148 * UI.SCALE, 133*UI.SCALE).row();

        top.add(closeButton()).expand().right().size(22 * UI.SCALE, 24 * UI.SCALE).pad(UI.SCALE, 0, 0, UI.SCALE);

        scrollPane.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                UI.stage.setKeyboardFocus(scrollPane);
                UI.stage.setScrollFocus(scrollPane);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                UI.stage.setKeyboardFocus(null);
                UI.stage.setScrollFocus(null);
            }

        });
    }

    public void fillScrollTable(List<GameObject> objects) {
        scrollTable.clear();
        scrollTable.top();

        int i = 0;
        for (GameObject o : objects) {
            i++;
            ImageButton button = UIImageButton.create(UIImageButton.buySlotStyle, Assets.getSprite("close-icon"));
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    //TODO: CLICK
                }
            });
            button.addListener(new InputListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    //TODO: HOVER
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    //TODO: HOVER
                }
            });

            scrollTable.add(button).size(48 * UI.SCALE, 48 * UI.SCALE).top();  // Align each button to the top

            if (i % 3 == 0) {
                scrollTable.row();
            }
        }
    }
}
