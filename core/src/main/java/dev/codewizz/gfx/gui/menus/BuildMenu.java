package dev.codewizz.gfx.gui.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import dev.codewizz.gfx.gui.UI;
import dev.codewizz.gfx.gui.elements.UIIconButton;
import dev.codewizz.gfx.gui.elements.UIIconMenu;
import dev.codewizz.gfx.gui.elements.UITextTooltip;
import dev.codewizz.gfx.gui.layers.GameLayer;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Logger;

public class BuildMenu extends UIIconMenu {
    public final static String ID = "build";

    public BuildMenu(UIIconButton parent) {
        super(parent);

        UIIconButton objects = UIIconButton.create("construction-icon");
        objects.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                UI.getLayer().openMenu(ObjectMenu.ID);
            }
        });
        objects.addListener(UITextTooltip.create("Objects"));

        UIIconButton structure = UIIconButton.create("new-build-icon");
        structure.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                UI.getLayer().openMenu(StructureMenu.ID);
            }
        });
        structure.addListener(UITextTooltip.create("Buildings"));

        addIcon(objects);
        addIcon(structure);
    }
}
