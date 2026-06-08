package dev.codewizz.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import dev.codewizz.world.inventory.Item;
import dev.codewizz.world.inventory.recipes.Recipe;
import dev.codewizz.world.inventory.types.ItemType;
import net.mgsx.gltf.loaders.gltf.GLTFLoader;
import net.mgsx.gltf.scene3d.scene.SceneAsset;

import java.util.HashMap;

public class Assets {

    public static final String ID = "vxr";

    private static final HashMap<String, Model> models = new HashMap<>();
    private static final HashMap<String, Sprite> sprites = new HashMap<>();
    private static final HashMap<String, TextureAtlas> atlases = new HashMap<>();

    public static void load() {
        atlases.put("ui", new TextureAtlas(Gdx.files.internal("packs/ui.atlas")));

        for (TextureAtlas t : atlases.values()) {
            for (TextureAtlas.AtlasRegion s : t.getRegions()) {
                sprites.put(s.name, t.createSprite(s.name));
            }
        }

        Recipe.register("vxr:planks", Recipe.fromJson("data/recipes/planks.json"));
        Recipe.register("vxr:firewood", Recipe.fromJson("data/recipes/firewood.json"));
    }

    public static Model findModel(String id) {
        if (models.containsKey(id)) {
            return models.get(id);
        }

        String[] split = id.split(":");
        String moduleIdentifier = split[0];
        String modelIdentifier = split[1];

        if (moduleIdentifier.equals(ID)) {
            FileHandle handle = Gdx.files.internal("models/" + modelIdentifier + ".gltf");
            SceneAsset asset = new GLTFLoader().load(handle);

            models.put(id, asset.scene.model);
            return models.get(id);
        } else {
            //TODO: find root of modules somehow as FileHandle
            return models.get(id);
        }
    }

    public static Sprite findSprite(String id) {
        if (sprites.containsKey(id)) {
            return sprites.get(id);
        } else  {
            Logger.error("Sprite not found: " + id);
            return null;
        }
    }
}
