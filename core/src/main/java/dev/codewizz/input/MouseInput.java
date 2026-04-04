package dev.codewizz.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import dev.codewizz.gfx.Camera;
import dev.codewizz.input.result.PickChunkResult;
import dev.codewizz.input.result.PickObjectResult;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.World;
import dev.codewizz.world.objects.Beacon;
import dev.codewizz.world.settlement.Settlement;
import dev.codewizz.world.voxel.Chunk;
import dev.codewizz.world.voxel.VoxelData;

public class MouseInput implements InputProcessor {

    private final Camera camera;
    private final World world;

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
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
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
