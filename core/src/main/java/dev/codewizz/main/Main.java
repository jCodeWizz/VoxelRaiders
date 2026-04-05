package dev.codewizz.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ai.GdxAI;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.input.MouseInput;
import dev.codewizz.utils.Logger;
import dev.codewizz.world.World;
import dev.codewizz.world.objects.Cow;
import dev.codewizz.world.objects.behaviour.pathfinding.NavAgent;
import dev.codewizz.world.objects.behaviour.pathfinding.NavCell;

public class Main extends ApplicationAdapter {

    public static Main instance;

    private Renderer renderer;

    public World world;

    @Override
    public void create () {
        Logger.log("Creating world...");
        Logger.warn("Warning");
        Logger.error("Shit went wrong!");
        Logger.error("Shit went wrong!", new RuntimeException("uh oh!!!!"));

        instance = this;

        renderer = new Renderer();
        world = new World();
        for (int i = 0; i < 5; i++) {
            Cow cow = new Cow();
            cow.getPosition().y = 10.5f;
            world.addObject(cow);
        }
        Gdx.input.setInputProcessor(new InputMultiplexer(renderer.getUiStage(), new MouseInput(renderer.getCamera(), world), renderer.getCamera().getInputController()));
    }

    @Override
    public void render () {
        GdxAI.getTimepiece().update(Gdx.graphics.getDeltaTime());

        world.update(Gdx.graphics.getDeltaTime());
        renderer.render(world.getObjectsToRender(), world.getChunksToRender());
    }

    @Override
    public void dispose () {
        renderer.dispose();
    }
}
