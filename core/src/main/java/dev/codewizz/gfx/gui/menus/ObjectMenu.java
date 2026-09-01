package dev.codewizz.gfx.gui.menus;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import dev.codewizz.gfx.gui.UI;
import dev.codewizz.utils.Assets;

public class ObjectMenu extends Menu {

    public static final String ID = "object";

    @Override
    protected void setup() {
        Table main = new Table();
        main.setBackground(new SpriteDrawable(Assets.getSprite("buy-menu")));
        base.add(main).size(262 * UI.SCALE, 440 * UI.SCALE).expand().left();
    }
}
