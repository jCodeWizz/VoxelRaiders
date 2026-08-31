package dev.codewizz.gfx.gui.elements;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import dev.codewizz.gfx.gui.UI;
import dev.codewizz.gfx.gui.menus.Menu;

import java.util.ArrayList;

public abstract class UIIconMenu extends Menu {

    private final ArrayList<UIIconButton> icons;
    private final UIIconButton parent;
    private final Table main;

    public UIIconMenu(UIIconButton parent) {
        this.parent = parent;
        this.icons = new ArrayList<>();

        main = new Table();
        main.setBackground(new TextureRegionDrawable(createBackground(0.7f)));
        base.add(main);
    }

    public void addIcon(UIIconButton ic) {
        icons.add(ic);
        main.clear();

        for(int i = 0; i < icons.size(); i++) {
            UIIconButton icon = icons.get(i);

            if(i == icons.size() - 1) {
                main.add(icon).size(22 * UI.SCALE, 24 * UI.SCALE).pad(5, 5, 24 * UI.SCALE + 10, 5);
                main.row();
            } else {
                main.add(icon).size(22 * UI.SCALE, 24 * UI.SCALE).pad(5, 5, 0, 5);
                main.row();
            }
        }
    }

    @Override
    protected void setup() {
        // No setup needed
    }

    @Override
    public void onOpen() {
        setPositionRelativeToParent();
    }

    private void setPositionRelativeToParent() {
        // Get the parent's position in stage coordinates
        Vector2 parentPosition = parent.localToStageCoordinates(new Vector2(0, 0));

        // Calculate the position for the menu
        float menuX = parentPosition.x;
        float menuY = parentPosition.y; // Position above the parent button

        // Set the menu's position
        main.setPosition(menuX - 5, menuY - 5);
    }
}
