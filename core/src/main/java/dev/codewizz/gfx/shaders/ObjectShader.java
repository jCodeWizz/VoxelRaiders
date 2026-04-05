package dev.codewizz.gfx.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;

public class ObjectShader extends DefaultShader {

    public ObjectShader(Renderable renderable, Config config) {
        super(renderable, config);
    }

    public static class Config extends DefaultShader.Config {
        public Config() {
            super(
                Gdx.files.internal("shaders/object.vert").readString(),
                Gdx.files.internal("shaders/object.frag").readString()
            );
        }
    }
}
