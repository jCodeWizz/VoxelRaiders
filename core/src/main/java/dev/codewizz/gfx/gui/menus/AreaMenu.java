package dev.codewizz.gfx.gui.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import dev.codewizz.gfx.gui.elements.UIIconButton;
import dev.codewizz.gfx.gui.elements.UIIconMenu;
import dev.codewizz.gfx.gui.elements.UITextTooltip;
import dev.codewizz.utils.Logger;

public class AreaMenu extends UIIconMenu {

    public final static String ID = "area";

    public AreaMenu(UIIconButton parent) {
        super(parent);

        UIIconButton farm = UIIconButton.create("tool-icon");
        farm.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Logger.log("area");
            }
        });
        farm.addListener(UITextTooltip.create("Farm"));

        addIcon(farm);
    }
}
