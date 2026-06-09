package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g3d.Model;
import dev.codewizz.world.inventory.Item;

import java.util.List;

public interface IBuy {

    Model getIconModel();
    String getName();
    String getDescription();
    List<Item> getCosts();
}
