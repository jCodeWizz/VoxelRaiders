package dev.codewizz.gfx.ui.panels;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import dev.codewizz.gfx.ui.UI;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.objects.IBuy;
import dev.codewizz.world.objects.SmallPile;

import java.util.ArrayList;
import java.util.List;

public class ObjectPanel extends Panel{

    private final static ArrayList<IBuy> objects = new ArrayList<>();
    static {
        objects.add(new SmallPile());
    }

    private IBuy selected;

    public ObjectPanel() {
        super(Assets.findSprite("path-menu"));
    }

    @Override
    void setup() {
        Table top = new Table();
        Table categories = new Table();
        Table list = new Table();
        Table info = new Table();

        content.add(top).expand().fillX().height(25 * UI.SCALE).row();
        content.add(categories).expand().fillX().height(24 * UI.SCALE).row();
        content.add(list).expand().fillX().height(260 * UI.SCALE).row();
        content.add(info).expand().fillX().height(131 * UI.SCALE).row();

        Table buySlots = new Table();
        list.add(buySlots).size(256*UI.SCALE);
        for (int i = 0; i < objects.size(); i++) {
            buySlots.add(buySlot(objects.get(0))).size(85 * UI.SCALE);
            if ((i + 1) % 3 == 0) {
                buySlots.row();
            }
        }

        top.add(new Label("Objects", UI.skin)).expand().left().padLeft(4 *  UI.SCALE);
        ImageButton closeButton = closeIcon();
        top.add(closeButton).size(22 * UI.SCALE, 24 * UI.SCALE).expand().right().padRight(UI.SCALE).padTop(UI.SCALE);
        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                close();
            }
        });
    }

    private ImageButton buySlot(IBuy info) {
        ImageButton.ImageButtonStyle style =
            new ImageButton.ImageButtonStyle(UI.skin.get("buyslot", ImageButton.ImageButtonStyle.class));
        style.imageUp = new TextureRegionDrawable(createPreview(info.getIconModel()));

        ImageButton buySlot = new ImageButton(style);
        buySlot.getImageCell().expand().fill();

        buySlot.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selected = info;
            }
        });


        return buySlot;
    }
}
