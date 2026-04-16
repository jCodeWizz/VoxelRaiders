package dev.codewizz.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ai.GdxAI;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.input.KeyInput;
import dev.codewizz.input.MouseInput;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.Logger;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.World;
import dev.codewizz.world.objects.Cow;
import dev.codewizz.world.objects.Tree;

public class Main extends ApplicationAdapter {

    public static Main instance;

    public Renderer renderer;

    public World world;

    public static int gameSpeed = 1;

    @Override
    public void create () {
        Logger.log("Starting game...");
        instance = this;

        Logger.log("Loading assets...");
        Assets.load();
        Logger.log("Loaded assets");

        Logger.log("Creating renderer...");
        renderer = new Renderer();
        Logger.log("Renderer created");

        Logger.log("Creating world...");
        world = new World();
        Logger.log("World created");

        for (int i = 0; i < 5; i++) {
            Cow cow = new Cow();
            cow.getPosition().y = 10.5f;
            world.addObject(cow);
        }

        Gdx.input.setInputProcessor(new InputMultiplexer(renderer.getUiStage(), new MouseInput(renderer.getCamera(), world), new KeyInput(renderer.getCamera(), world), renderer.getCamera().getInputController()));
        Logger.log("Game started");
    }

    @Override
    public void render () {


        for (int i = 0; i < gameSpeed; i++) {
            GdxAI.getTimepiece().update(Gdx.graphics.getDeltaTime() / (float)gameSpeed);
            world.update(Gdx.graphics.getDeltaTime() / (float)gameSpeed);
        }

        renderer.render(world.getObjectsToRender(), world.getChunksToRender());
    }

    @Override
    public void dispose () {
        renderer.dispose();
    }
}
