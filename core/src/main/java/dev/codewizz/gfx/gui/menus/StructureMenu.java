package dev.codewizz.gfx.gui.menus;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import dev.codewizz.gfx.gui.UI;
import dev.codewizz.gfx.gui.elements.UIIconButton;
import dev.codewizz.input.MouseInput;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Logger;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.objects.Gatherable;
import dev.codewizz.world.objects.behaviour.templates.GatherTemplate;
import dev.codewizz.world.settlement.structures.Structure;

import java.util.List;

public class StructureMenu extends Menu {

    public static final String ID = "structure";

    private Structure structure;

    @Override
    public void onOpen() {
        structure = new Structure();
        Main.instance.getWorld().addObject(structure);
    }

    @Override
    protected void setup() {
        Table main = new Table();
        base.add(main).expand().fill();

        Table buttons = new Table();
        buttons.setBackground(createBackground(0.5f));
        main.add(buttons).expand().left().padLeft(2 * UI.SCALE);

        UIIconButton room = UIIconButton.create("plus-icon");
        buttons.add(room).size(22 * UI.SCALE, 24 * UI.SCALE).pad(3 * UI.SCALE).row();
        room.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MouseInput.pickAreaListener = (min, max) -> {
                    structure.addRoom(min, max);
                };
            }
        });

        UIIconButton b = UIIconButton.create("build-icon");
        buttons.add(b).size(22 * UI.SCALE, 24 * UI.SCALE).pad(3 * UI.SCALE).row();

        UIIconButton done = UIIconButton.create("done-icon");
        buttons.add(done).size(22 * UI.SCALE, 24 * UI.SCALE).pad(3 * UI.SCALE);
        done.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
    }
}
