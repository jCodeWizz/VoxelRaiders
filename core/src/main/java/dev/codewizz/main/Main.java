package dev.codewizz.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.GdxAI;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.world.World;
import dev.codewizz.world.objects.Cow;
import dev.codewizz.world.objects.Terrain;

public class Main extends ApplicationAdapter {

    public static Main instance;

    private Renderer renderer;

    public World world;

    @Override
    public void create () {
        instance = this;


        renderer = new Renderer();

        world = new World();
        world.addObject(new Terrain());
        world.addObject(new Cow());
    }

    @Override
    public void render () {
        GdxAI.getTimepiece().update(Gdx.graphics.getDeltaTime());
        world.update(Gdx.graphics.getDeltaTime());
        renderer.render(world.getObjectsToRender());
    }

    @Override
    public void dispose () {
        renderer.dispose();
    }
}
