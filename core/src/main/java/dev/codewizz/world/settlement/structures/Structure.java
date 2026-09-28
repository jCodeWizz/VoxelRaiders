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
import dev.codewizz.world.GameObject;
import dev.codewizz.world.GameObjectInfo;
import dev.codewizz.world.objects.Tree;

import java.util.ArrayList;
import java.util.List;

public class Structure extends GameObject {
    public static final GameObjectInfo INFO = new GameObjectInfo("vxr:structure", "Structure", "", Structure.class);

    private static final Material MATERIAL = new Material(ColorAttribute.createDiffuse(new Color(0.1f, 0.1f, 0.3f, 1.0f)));
    private static final ModelBuilder BUILDER = new ModelBuilder();

    private final List<Room> rooms = new ArrayList<>();

    private ModelInstance instance;

    public Structure() {
        super(INFO.getId());
    }

    public void addRoom(Vector3 min, Vector3 max) {
        Room room = new Room(min, max);
        rooms.add(room);

        refreshModel();
    }

    public void refreshModel() {
        Vector3 position = new Vector3(rooms.get(0).getA());
        for (Room room : rooms) {
            if (room.getA().x  < position.x) {
                position.x = room.getA().x;
            }
            if (room.getA().y  < position.y) {
                position.y = room.getA().y;
            }
            if (room.getA().z  < position.z) {
                position.z = room.getA().z;
            }
        }
        this.getPosition().set(position);

        BUILDER.begin();

        for (Room room : rooms) {
            MeshPartBuilder part = BUILDER.part(
                "room_" + room,
                GL20.GL_TRIANGLES,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal,
                MATERIAL
            );

            part.box(
                room.getB().x - room.getA().x,
                1f,
                room.getB().z - room.getA().z
            );
        }

        instance = new ModelInstance(BUILDER.end());
    }

    public boolean isCompleted() {
        for (Room room : rooms) {
            if (!room.isCompleted()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderObjectInstance(this, instance);
    }
}
