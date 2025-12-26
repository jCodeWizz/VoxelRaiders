package dev.codewizz.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g3d.Model;
import net.mgsx.gltf.loaders.gltf.GLTFLoader;
import net.mgsx.gltf.scene3d.scene.SceneAsset;

import java.util.HashMap;

public class Assets {

    public static final String ID = "vxr";

    private static final GLTFLoader loader = new GLTFLoader();
    private static final HashMap<String, Model> models = new HashMap<>();

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
}
