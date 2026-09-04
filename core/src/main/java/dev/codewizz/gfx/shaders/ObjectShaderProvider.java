package dev.codewizz.gfx.shaders;

import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.utils.DefaultShaderProvider;
import dev.codewizz.gfx.attributes.PlaceholderAttribute;

public class ObjectShaderProvider extends DefaultShaderProvider {

    private final ObjectShader.Config objectConfig =
        new ObjectShader.Config();

    private final PlaceholderShader.Config placeholderConfig =
        new PlaceholderShader.Config();

    @Override
    protected Shader createShader(Renderable renderable) {

        if (renderable.material.has(PlaceholderAttribute.Type)) {
            return new PlaceholderShader(
                renderable,
                placeholderConfig
            );
        }

        return new ObjectShader(
            renderable,
            objectConfig
        );
    }
}
