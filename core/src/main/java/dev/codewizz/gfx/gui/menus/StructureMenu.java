package dev.codewizz.gfx.gui.menus;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.codewizz.gfx.gui.UI;
import dev.codewizz.gfx.gui.elements.UIIconButton;

public class StructureMenu extends Menu {

    public static final String ID = "structure";

    @Override
    protected void setup() {
        Table main = new Table();
        base.add(main).expand().fill();

        Table buttons = new Table();
        buttons.setBackground(createBackground(0.5f));
        main.add(buttons).expand().left().padLeft(2 * UI.SCALE);

        UIIconButton room = UIIconButton.create("build-icon");
        buttons.add(b).size(22 * UI.SCALE, 24 * UI.SCALE).pad(3 * UI.SCALE);

        UIIconButton b = UIIconButton.create("build-icon");
        buttons.add(b).size(22 * UI.SCALE, 24 * UI.SCALE).pad(3 * UI.SCALE);

        UIIconButton b = UIIconButton.create("build-icon");
        buttons.add(b).size(22 * UI.SCALE, 24 * UI.SCALE).pad(3 * UI.SCALE);

        UIIconButton done = UIIconButton.create("build-icon");
        buttons.add(b).size(22 * UI.SCALE, 24 * UI.SCALE).pad(3 * UI.SCALE);
    }
}
