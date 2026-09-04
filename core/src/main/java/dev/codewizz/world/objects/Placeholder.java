package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.main.Main;
import dev.codewizz.main.Registers;
import dev.codewizz.utils.Logger;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.GameObjectInfo;
import dev.codewizz.world.GameObjectInfoShop;
import dev.codewizz.world.inventory.Inventory;
import dev.codewizz.world.inventory.Item;

public class Placeholder extends GameObject {
    public static final GameObjectInfo INFO = new GameObjectInfo("vxr:placeholder", "Object to be", "", Placeholder.class);

    private final Inventory inventory;
    private GameObjectInfoShop info;
    private ModelInstance modelInstance;

    public Placeholder() {
        super(INFO.getId());

        this.name = "Placeholder";
        this.description = "";

        this.inventory = new Inventory();
    }

    public void setInfo(GameObjectInfoShop info) {
        this.info = info;
        this.modelInstance = new ModelInstance(this.info.getModel());
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderObjectInstance(this, modelInstance);
    }

    public void addItem(Item item) {
        inventory.addItem(item);

        for (Item i : info.getCosts()) {
            if (!inventory.containsItem(i)) {
                return;
            }
        }

        GameObject object = Registers.createObject(info.getId());
        object.getPosition().set(getPosition());
        Main.instance.getWorld().addObject(object);

        this.destroy();
    }
}
