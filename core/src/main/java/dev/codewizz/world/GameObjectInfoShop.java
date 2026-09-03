package dev.codewizz.world;

import com.badlogic.gdx.graphics.g3d.Model;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.inventory.Item;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameObjectInfoShop extends GameObjectInfo {

    private final Model model;
    private final List<Item> costs;

    public GameObjectInfoShop(String id, String name, String description, Class<? extends GameObject> clazz, String model, Item... costs) {
        super(id, name, description, clazz);

        this.costs = new ArrayList<>();
        this.costs.addAll(Arrays.asList(costs));
        this.model = Assets.findModel(model);
    }

    public Model getModel() {
        return model;
    }

    public List<Item> getCosts() {
        return costs;
    }
}
