package dev.codewizz.gfx.gui.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.codewizz.gfx.gui.UI;
import dev.codewizz.gfx.gui.elements.UILabel;
import dev.codewizz.utils.Assets;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NotificationMenu extends Menu {

    public final static String ID = "notification";

    private static List<Notification> notifications;

    private static Table main;

    public NotificationMenu() {
        this.shouldClose = false;
    }

    public static void makeNotification(Sprite sprite, String title, String text) {
        Notification no = new Notification(sprite, title, text);

        if (notifications.size() >= 5) {
            notifications.remove(0);
        }

        notifications.add(no);

        refresh();
    }

    @Override
    public void render(SpriteBatch b) {
        for (Notification no : notifications) {
            no.time -= Gdx.graphics.getDeltaTime();
            if (no.time < 0) {
                notifications.remove(no);
            }
        }


        refresh();
    }

    @Override
    protected void setup() {
        notifications = new CopyOnWriteArrayList<>();

        main = new Table();
        base.add(main).expand().right().top().pad(10 * UI.SCALE, 0, 0, 10 * UI.SCALE);

        refresh();
    }

    private static void refresh() {
        main.clear();

        for (Notification notification : notifications) {
            main.add(notification.table).size(256 * UI.SCALE, 36 * UI.SCALE).padBottom(3 * UI.SCALE);
            main.row();
        }
    }
}
class Notification {

    Table table;

    float time = 15f;

    public Notification(Sprite sprite, String title, String text) {
        table = new Table();
        table.setBackground(new Image(Assets.getSprite("notification")).getDrawable());

        Image image  = new Image(sprite);
        table.add(image).pad(2 * UI.SCALE).size(32 * UI.SCALE);

        Table textTable = new Table();
        table.add(textTable).expand().fill();

        UILabel titleLabel = UILabel.create(title, UILabel.defaultStyle);
        textTable.add(titleLabel).expand().left().padLeft(6 * UI.SCALE);

        textTable.row();

        UILabel textLabel = UILabel.create(text, UILabel.mediumStyle);
        textTable.add(textLabel).expand().left().padLeft(UI.SCALE);
    }
}
