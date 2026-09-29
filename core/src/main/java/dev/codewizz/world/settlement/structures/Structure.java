package dev.codewizz.world.settlement.structures;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.utils.Logger;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.GameObjectInfo;
import dev.codewizz.world.objects.Tree;

import java.util.ArrayList;
import java.util.List;

public class Structure extends GameObject {
    public static final GameObjectInfo INFO = new GameObjectInfo("vxr:structure", "Structure", "", Structure.class);
    private static final StructureBuilder BUILDER = new StructureBuilder();

    private final List<Room> rooms = new ArrayList<>();
    private ModelInstance instance;

    public Structure() {
        super(INFO.getId());

        instance = new ModelInstance(new Model());
    }

    public void addRoom(Vector3 min, Vector3 max) {
        int width = (int) (max.x - min.x);
        int depth = (int) (max.z - min.z);

        Room room = new Room(min, width, depth);
        rooms.add(room);

        Logger.log("Pos: " + room.getPosition());
        Logger.log("Max: " +  room.getWidth() + " ; Depth: " + room.getDepth());

        refreshModel();
    }

    public void refreshModel() {
//        Vector3 position = new Vector3(rooms.get(0).getPosition());
//        for (Room room : rooms) {
//            if (room.getPosition().x  < position.x) {
//                position.x = room.getPosition().x;
//            }
//            if (room.getPosition().y  < position.y) {
//                position.y = room.getPosition().y;
//            }
//            if (room.getPosition().z  < position.z) {
//                position.z = room.getPosition().z;
//            }
//        }
//        this.getPosition().set(position);
        this.getPosition().y = 5.25f;

        instance = new ModelInstance(BUILDER.build(this));
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderObjectInstance(this, instance);
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
