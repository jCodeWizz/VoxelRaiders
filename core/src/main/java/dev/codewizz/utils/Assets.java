package dev.codewizz.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.Model;
import net.mgsx.gltf.loaders.gltf.GLTFLoader;
import net.mgsx.gltf.scene3d.scene.SceneAsset;

import java.util.HashMap;

public class Assets {

    public static final String ID = "vxr";

    private static final GLTFLoader loader = new GLTFLoader();
    private static final HashMap<String, Model> models = new HashMap<>();
    private static final HashMap<String, Sprite> sprites = new HashMap<>();

    public static void load() {
        sprites.put("icon-board-extension", new Sprite(new Texture(Gdx.files.internal("ui/drawables/icon-board-extension.png"))));
        sprites.put("icon-board", new Sprite(new Texture(Gdx.files.internal("ui/drawables/icon-board.png"))));
        sprites.put("build-icon", new Sprite(new Texture(Gdx.files.internal("ui/drawables/build-icon.png"))));
        sprites.put("close-icon", new Sprite(new Texture(Gdx.files.internal("ui/drawables/close-icon.png"))));
        sprites.put("path-menu", new Sprite(new Texture(Gdx.files.internal("ui/drawables/path-menu.png"))));
    }

    public static Model findModel(String id) {
        if (models.containsKey(id)) {
            return models.get(id);
        }

        String moduleIdentifier = id.split(":")[0];
        String modelIdentifier = id.split(":")[1];

        if (moduleIdentifier.equals(ID)) {
            FileHandle handle = Gdx.files.internal("models/" + modelIdentifier + ".gltf");
            SceneAsset asset = loader.load(handle);

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
