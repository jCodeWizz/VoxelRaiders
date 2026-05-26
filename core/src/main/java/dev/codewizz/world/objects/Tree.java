package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.gfx.ui.UI;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.WUtils;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.inventory.Item;
import dev.codewizz.world.inventory.types.ItemType;
import dev.codewizz.world.objects.behaviour.templates.ChangeObjectTemplate;

public class Tree extends Gatherable {

    private final ModelInstance instance;
    private final ModelInstance choppedInstance;

    private boolean chopped = false;

    public Tree() {
        super("vxr:tree");

        this.name = "Tree";
        this.description = "For all your oxygen needs";

        this.instance = new ModelInstance(Assets.findModel(getId()));
        this.choppedInstance = new ModelInstance(Assets.findModel("vxr:tree-stump"));

        this.getRotation().setFromAxis(Vector3.Y, WUtils.getRandom(0, 360));
        float scale = WUtils.RANDOM.nextFloat() * 0.3f + 0.8f;
        this.getScale().set(scale, scale, scale);
    }

    @Override
    public void render(Renderer renderer) {
        if (chopped) {
            renderer.renderObjectInstance(this, choppedInstance);
        } else {
            renderer.renderObjectInstance(this, instance);
        }
    }

    @Override
    public void gather(Hermit hermit) {
        if (chopped) {
            destroy();
        } else {
            hermit.getInventory().addItem(new Item(ItemType.LOG, WUtils.getRandom(3, 5)));
            chopped = true;
        }
    }

    @Override
    public void addButtonsToSelectMenu(GameObject target, Table buttons) {
        if (chopped) {
            ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle(UI.skin.get("stretch", ImageButton.ImageButtonStyle.class));
            style.imageUp = new SpriteDrawable(Assets.findSprite("close-icon"));
            ImageButton button = new ImageButton(style);
            button.getImageCell().expand().fill();

            buttons.add(button).size(22 * UI.SCALE, 24 * UI.SCALE).expand().top();
            TextTooltip tooltip = new TextTooltip("Convert to Chopping Block", UI.skin);
            button.addListener(tooltip);
            tooltip.setInstant(true);
            button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Main.instance.getWorld().getSettlement().addTask(new ChangeObjectTemplate(target, new TreeStump()));
                }
            });
        }
    }
}
