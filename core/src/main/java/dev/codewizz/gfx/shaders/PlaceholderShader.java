package dev.codewizz.gfx.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;

public class PlaceholderShader extends DefaultShader {

    public PlaceholderShader(Renderable renderable, PlaceholderShader.Config config) {
        super(renderable, config);
    }

    @Override
    public void begin(Camera camera, RenderContext context) {
        super.begin(camera, context);

        context.setBlending(
            true,
            GL20.GL_SRC_ALPHA,
            GL20.GL_ONE_MINUS_SRC_ALPHA
        );

        context.setDepthMask(false);
    }

    @Override
    public void end() {
        context.setDepthMask(true);
        super.end();
    }

    public static class Config extends DefaultShader.Config {
        public Config() {
            super(
                Gdx.files.internal("shaders/placeholder.vert").readString(),
                Gdx.files.internal("shaders/placeholder.frag").readString()
            );
        }
    }
}
