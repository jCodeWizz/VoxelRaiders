package dev.codewizz.input.console;

import com.badlogic.gdx.graphics.Color;
import dev.codewizz.gfx.gui.elements.UILabel;
import dev.codewizz.gfx.gui.menus.ConsoleMenu;
import dev.codewizz.utils.Logger;

public class Console {

    public static ConsoleMenu menu;

    public void register() {
    }

    public static void printLine(String text) {
        printLine(text, Color.WHITE);
    }

    public static void printLine(String text, Color color) {
        if (menu == null) return;
        UILabel l = UILabel.create("[" + Logger.time() + "] " + text, UILabel.smallStyle);
        l.setColor(color);

        menu.lines.add(l);
        menu.refresh();
    }
}
