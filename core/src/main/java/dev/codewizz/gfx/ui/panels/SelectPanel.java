package dev.codewizz.gfx.ui.panels;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.codewizz.gfx.ui.UI;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.Logger;
import dev.codewizz.world.Entity;
import dev.codewizz.world.GameObject;

public class SelectPanel extends Panel {

    private final GameObject target;

    private Label name;
    private Label description;
    private Label health;

    private Table buttons;

    public SelectPanel(GameObject target) {
        super(Assets.findSprite("select-menu"));
        this.target = target;
        target.addButtonsToSelectMenu(target, buttons);
    }

    private void updateData() {
        if (target instanceof Entity) {
            name.setText(target.getName());
            description.setText(target.getDescription());
            health.setText("Health");
        } else {
            name.setText(target.getName());
            description.setText(target.getDescription());
            health.setText("");
        }
    }

    @Override
    public void update() {
        Vector3 screenPos = Main.instance.getRenderer().getCamera().getPerspectiveCamera().project(new Vector3(target.getPosition()));
        setPosition(screenPos.x - content.getWidth() / 2f, screenPos.y);

        updateData();
    }

    @Override
    void setup() {
        name = new Label("", UI.skin);
        description = new Label("", UI.skin);
        description.setWrap(true);
        health = new Label("", UI.skin);
        Image image = new Image(Assets.findSprite("select-menu-head"));

        Table left = new Table();
        Table right = new Table();

        left.add(image).size(48 * UI.SCALE);
        content.add(left).pad(3 *  UI.SCALE, 3 * UI.SCALE, 3 * UI.SCALE, 0).size(48 * UI.SCALE).left();

        right.add(name).expandX().fillX().left().row();
        right.add(description).expandX().fillX().left().row();
        right.add(health).expandX().fillX().left().row();

        buttons = new Table();
        content.add(right).expand().fill().right();
        content.add(buttons).size(22 * UI.SCALE, 54 * UI.SCALE).right();
    }
}
