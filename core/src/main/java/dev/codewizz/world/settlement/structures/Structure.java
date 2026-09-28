package dev.codewizz.world.settlement.structures;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;
import java.util.List;

public class Structure {

    private Model model;
    private ModelInstance instance;
    private final Vector3 position;

    private final List<Room> rooms = new ArrayList<>();

    public Structure(Vector3 position) {
        this.position = position;
    }

    public void create() {
        model = new Model();
        instance = new ModelInstance(model);
    }

    public boolean isCompleted() {
        for (Room room : rooms) {
            if (!room.isCompleted()) {
                return false;
            }
        }

        return true;
    }
}
