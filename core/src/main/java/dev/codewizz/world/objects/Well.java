package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.GameObjectInfo;

public class Well extends GameObject {
    public static final GameObjectInfo INFO = new GameObjectInfo("vxr:well", "Well", "Marker of your home base, a true treasure", Well.class);

    private final ModelInstance instance;

    public Well() {
        super(INFO.getId());

        this.name = "Well";
        this.description = "Marker of your home base, a true treasure";

        instance = new ModelInstance(Assets.findModel(getId()));
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderObjectInstance(this, instance);
    }
}
