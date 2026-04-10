package dev.codewizz.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import dev.codewizz.gfx.Camera;
import dev.codewizz.input.result.PickChunkResult;
import dev.codewizz.input.result.PickObjectResult;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Logger;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.World;
import dev.codewizz.world.objects.Beacon;
import dev.codewizz.world.objects.Gatherable;
import dev.codewizz.world.objects.behaviour.pathfinding.NavAgent;
import dev.codewizz.world.objects.behaviour.pathfinding.NavCell;
import dev.codewizz.world.objects.behaviour.templates.GatherTemplate;
import dev.codewizz.world.settlement.Settlement;
import dev.codewizz.world.voxel.Chunk;
import dev.codewizz.world.voxel.VoxelData;

public class MouseInput implements InputProcessor {

    private final Camera camera;
    private final World world;

    private static Vector3 dragPosition = null;
    private static final Material selectMaterial = new Material(ColorAttribute.createDiffuse(new Color(0, 1, 0, 1f)), new IntAttribute(IntAttribute.CullFace, 0));
    private static final ModelBuilder modelBuilder = new ModelBuilder();
    private static final Model selectModel = modelBuilder.createRect(
        -0.5f, 0f, -0.5f,   // corner 1
        0.5f, 0f, -0.5f,   // corner 2
        0.5f, 0f, 0.5f,   // corner 3
        -0.5f, 0f, 0.5f,   // corner 4
        0, -1, 0,            // normal (up)
        selectMaterial,
        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal
    );
    private static final ModelInstance selectModelInstance = new ModelInstance(selectModel);

    public MouseInput(Camera camera, World world) {
        this.camera = camera;
        this.world = world;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (world.getSettlement() == null) {
            PickChunkResult result = pickChunk(camera, world, screenX, screenY);
            if (result.getChunk() != null) {
                GameObject beacon = new Beacon();
                beacon.getPosition().set(result.getIntersection());
                world.addObject(beacon);

                world.setSettlement(new Settlement(world, result.getIntersection()));

                return true;
            }
        } else {
            if (button == Input.Buttons.LEFT) {
                PickObjectResult objectResult = pickObject(camera, world, screenX, screenY);
                if (objectResult.getObject() != null) {

                    if (objectResult.getObject() instanceof Gatherable) {
                        Gatherable gatherable = (Gatherable) objectResult.getObject();

                        if (gatherable.isTasked()) return false;

                        Main.instance.world.getSettlement().addTask(new GatherTemplate(gatherable));
                        gatherable.setTasked(true);

                        return true;
                    }
                }
            }

            if (button == Input.Buttons.RIGHT) {
                PickChunkResult chunkResult = pickChunk(camera, world, screenX, screenY);
                if (chunkResult.getChunk() != null) {
                    dragPosition = new Vector3(chunkResult.getIntersection());
                    return true;
                } else {
                    dragPosition = null;
                    Logger.log("Reset");
                }
            }
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {


        if (button == Input.Buttons.RIGHT) {
            if (world.getSettlement() != null) {
                if (dragPosition != null) {
                    dragPosition = null;
                    selectModelInstance.transform
                        .setToTranslation(0, 10.52f, 0)
                        .scale(0, 1f, 0);
                }
            }
        }


        return false;
    }

    private static void updateSelectInstance(Camera camera, World world) {
        PickChunkResult chunkResult = pickChunk(camera, world, Gdx.input.getX(), Gdx.input.getY());
        if (chunkResult.getChunk() != null) {
            float cellSize = 0.5f;

            float minX = Math.min(dragPosition.x, chunkResult.getIntersection().x);
            float maxX = Math.max(dragPosition.x, chunkResult.getIntersection().x);

            float minZ = Math.min(dragPosition.z, chunkResult.getIntersection().z);
            float maxZ = Math.max(dragPosition.z, chunkResult.getIntersection().z);

            minX = (float)Math.floor(minX / cellSize) * cellSize;
            maxX = (float)Math.ceil (maxX / cellSize) * cellSize;

            minZ = (float)Math.floor(minZ / cellSize) * cellSize;
            maxZ = (float)Math.ceil (maxZ / cellSize) * cellSize;

            float width = maxX - minX;
            float depth = maxZ - minZ;

            float centerX = (minX + maxX) / 2f;
            float centerZ = (minZ + maxZ) / 2f;

            selectModelInstance.transform
                .setToTranslation(centerX, 10.52f, centerZ)
                .scale(width, 1f, depth);
        }
    }

    public static void update() {
        if (dragPosition != null) {
            updateSelectInstance(Main.instance.renderer.getCamera(), Main.instance.world);
        }
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public static PickObjectResult pickObject(Camera camera, World world, int screenX, int screenY) {
        Ray ray = camera.getPerspectiveCamera().getPickRay(screenX, screenY);
        PickObjectResult result = new PickObjectResult();
        Vector3 intersection = new Vector3();

        for (GameObject object : world.getObjectsToRender()) {
            BoundingBox bb = new BoundingBox();

            Vector3 center = object.getPosition();
            Vector3 offset = new Vector3(object.getSize()).scl(0.5f);
            bb.set(new Vector3(center).sub(offset), new Vector3(center).add(offset));

            if (Intersector.intersectRayBounds(ray, bb, intersection)) {
                float distance = ray.origin.dst2(intersection);

                if (distance < result.getDistance()) {
                    result.setDistance(distance);
                    result.setObject(object);
                    result.getIntersection().set(intersection);
                }
            }
        }

        return result;
    }

    public static PickChunkResult pickChunk(Camera camera, World world, int screenX, int screenY) {
        Ray ray = camera.getPerspectiveCamera().getPickRay(screenX, screenY);
        PickChunkResult result = new PickChunkResult();
        Vector3 intersection = new Vector3();

        for (int i = 0; i < world.getChunksToRender().length; i++) {
            for (int j = 0; j < world.getChunksToRender().length; j++) {
                Chunk c = world.getChunksToRender()[i][j];

                BoundingBox bb = new BoundingBox();
                c.getModelInstance().calculateBoundingBox(bb);
                bb.mul(c.getModelInstance().transform);

                if (Intersector.intersectRayBounds(ray, bb, intersection)) {
                    float distance = ray.origin.dst2(intersection);

                    if (distance < result.getDistance()) {
                        result.setDistance(distance);
                        result.setChunk(c);
                        result.getIntersection().set(intersection);
                    }
                }
            }
        }

        return result;
    }

    public static void renderSelectArea(ModelBatch batch) {
        if (dragPosition != null) {
            batch.render(selectModelInstance);
        }
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }
}
