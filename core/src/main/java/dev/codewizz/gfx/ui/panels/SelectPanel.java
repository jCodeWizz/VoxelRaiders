package dev.codewizz.gfx.ui.panels;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.GameObject;

public class SelectPanel extends Panel {

    private final GameObject target;

    public SelectPanel(GameObject target) {
        super(Assets.findSprite("select-menu"));

        this.target = target;
    }

    @Override
    public void update() {
        Vector3 screenPos = Main.instance.getRenderer().getCamera().getPerspectiveCamera().project(new Vector3(target.getPosition()));
        setPosition(screenPos.x, screenPos.y);
    }

    @Override
    void setup() {
        Table table = new Table();
        content.add(table).expand().size(64, 64);
    }
}
