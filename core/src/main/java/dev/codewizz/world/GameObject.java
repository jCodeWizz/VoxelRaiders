package dev.codewizz.world;

import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.main.Main;

public abstract class GameObject {

    private final Vector3 position;
    private final Quaternion rotation;
    private final Vector3 scale;
    private final Vector3 size;

    private final String id;
    protected String name;
    protected String description;

    public GameObject(String id) {
        this.id = id;
        this.name = "Unnamed Object";
        this.description = "This is quite boring, it might be useful however!";

        position = new Vector3();
        rotation = new Quaternion();
        scale = new Vector3(1, 1, 1);
        size = new Vector3(1,1 ,1);
    }

    public abstract void update(float dt);
    public abstract void render(Renderer renderer);

    public void onDestroy() {}

    public void destroy() {
        onDestroy();
        Main.instance.getWorld().removeObject(this);
    }

    public void addButtonsToSelectMenu(GameObject target, Table buttons)  {

    }

    public Vector3 getPosition() {
        return position;
    }

    public Quaternion getRotation() {
        return rotation;
    }

    public Vector3 getScale() {
        return scale;
    }

    public String getId() {
        return id;
    }

    public Vector3 getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
