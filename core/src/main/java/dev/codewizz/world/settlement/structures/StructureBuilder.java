package dev.codewizz.world.settlement.structures;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import dev.codewizz.utils.Assets;

public class StructureBuilder {

    private final static Model FLOOR = Assets.loadModel("floor");
    private final static Model WALL = Assets.loadModel("wall");

    public Model build(Structure structure) {
        Model result = new Model();

        for (Room room : structure.getRooms()) {
            addFloor(result, room, structure);
            addWalls(result, room, structure);
        }

        return result;
    }

    private void addWalls(Model result, Room room, Structure structure) {
        for (int x = 0; x < room.getWidth(); x++) {
            for (int z = 0; z < room.getDepth(); z++) {
                if (x == 0) {
                    addModel(
                        result,
                        WALL,
                        new Vector3(x + room.getPosition().x, structure.getPosition().y, z + 0.5f + room.getPosition().z),
                        "wall_" + x + "_" + z);
                }
                if (x == room.getWidth() - 1) {
                    addModel(
                        result,
                        WALL,
                        new Vector3(x + 1.0f + room.getPosition().x, structure.getPosition().y, z + 0.5f + room.getPosition().z),
                        "wall_" + x + "_" + z);
                }
                if (z == 0) {
                    Quaternion rotation = new Quaternion().setFromAxis(Vector3.Y, 90);

                    addModel(
                        result,
                        WALL,
                        new Vector3(x + 0.5f + room.getPosition().x, structure.getPosition().y, z + room.getPosition().z),
                        rotation,
                        "wall_" + x + "_" + z);
                }
                if (z == room.getDepth() - 1) {
                    Quaternion rotation = new Quaternion().setFromAxis(Vector3.Y, 90);

                    addModel(
                        result,
                        WALL,
                        new Vector3(x + 0.5f + room.getPosition().x, structure.getPosition().y, z + 1.0f + room.getPosition().z),
                        rotation,
                        "wall_" + x + "_" + z);
                }
            }
        }
    }

    private void addFloor(Model result, Room room, Structure structure) {
        for (int x = 0; x < room.getWidth(); x++) {
            for (int z = 0; z < room.getDepth(); z++) {
                addModel(
                    result,
                    FLOOR,
                    new Vector3(x + 0.5f + room.getPosition().x, structure.getPosition().y, z + 0.5f + room.getPosition().z),
                    "floor_" + x + "_" + z
                );
            }
        }
    }

    private void addModel(Model result, Model source, Vector3 position, String id) {
        Node node = new Node();
        node.id = id;

        node.translation.set(position);

        for (Node sourceNode : source.nodes) {
            copyNode(sourceNode, node);
        }

        result.nodes.add(node);
    }

    private void addModel(Model result, Model source, Vector3 position, Quaternion rotation, String id) {
        Node node = new Node();
        node.id = id;

        node.translation.set(position);
        node.rotation.set(rotation);

        for (Node sourceNode : source.nodes) {
            copyNode(sourceNode, node);
        }

        result.nodes.add(node);
    }

    private void copyNode(Node source, Node parent) {
        Node node = new Node();

        node.id = source.id;
        node.translation.set(source.translation);
        node.rotation.set(source.rotation);
        node.scale.set(source.scale);

        for (NodePart sourcePart : source.parts) {
            NodePart part = new NodePart();

            part.meshPart = new MeshPart(sourcePart.meshPart);
            part.material = sourcePart.material;

            node.parts.add(part);
        }

        for (Node child : source.getChildren()) {
            copyNode(child, node);
        }

        parent.addChild(node);
    }
}
