package dev.codewizz.gfx.shaders;

import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.graphics.g3d.utils.DefaultShaderProvider;

public class ObjectShaderProvider extends DefaultShaderProvider {

    private final ObjectShader.Config config = new ObjectShader.Config();

    @Override
    protected Shader createShader(Renderable renderable) {
        return new ObjectShader(renderable, config);
    }
}
