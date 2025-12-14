package dev.codewizz.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import dev.codewizz.gfx.Camera;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.world.World;
import dev.codewizz.world.objects.Prop;

public class Main extends ApplicationAdapter {

    private Renderer renderer;

    private World world;

    @Override
    public void create () {
        renderer = new Renderer();

        world = new World();
        world.addObject(new Prop());
    }

    @Override
    public void render () {
        renderer.render(world.getObjectsToRender());
    }

    @Override
    public void dispose () {
        renderer.dispose();
    }
}
