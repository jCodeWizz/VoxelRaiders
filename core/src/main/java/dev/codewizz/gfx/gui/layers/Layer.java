package dev.codewizz.gfx.gui.layers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import dev.codewizz.gfx.gui.elements.*;
import dev.codewizz.gfx.gui.menus.IUpdateDataMenu;
import dev.codewizz.gfx.gui.menus.Menu;
import dev.codewizz.utils.Timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Layer {

    public Map<String, Menu> menus = new HashMap<>();
    private final Timer updateTimer;

    public Layer() {
        updateTimer = new Timer(0.2f) {
            @Override
            public void timer() {
                for (Menu m : menus.values()) {
                    if (m.isOpen() && m instanceof IUpdateDataMenu) {
                        ((IUpdateDataMenu) m).updateData();
                    }
                }
            }
        };
        updateTimer.setRepeat(true);
    }

    public static void reload() {
        UILabel.reload();
        UIIconButton.reload();
        UIIconToggle.reload();
        UIImageButton.reload();
        UISlider.reload();
        UITextButton.reload();
        UITextField.reload();
    }

    public abstract void open(Stage stage);

    public void update(float d) {
        updateTimer.update(d);
    }

    public abstract void close(Stage stage);

    public void render(SpriteBatch b) {
        for (Menu m : menus.values()) {
            if (m.isOpen()) {
                m.render(b);
            }
        }
    }

    public void openMenu(String id) {

        Menu menu = menus.get(id);

        if (menu.isOpen()) {
            menu.close();
            return;
        }

        closeMenus();

        menu.open();
        if (menu instanceof IUpdateDataMenu) {
            ((IUpdateDataMenu) menu).updateData();
        }
    }

    public void closeMenus() {
        for (Menu m : menus.values()) {
            if (m.isOpen() && m.shouldClose()) {
                m.close();
            }
        }
    }

    public boolean menusClosed() {
        for (Menu m : menus.values()) {
            if (m.isOpen() && m.shouldClose()) {
                return false;
            }
        }
        return true;
    }

}
