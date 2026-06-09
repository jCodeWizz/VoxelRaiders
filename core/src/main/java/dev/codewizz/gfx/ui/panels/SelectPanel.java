package dev.codewizz.gfx.ui.panels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import dev.codewizz.gfx.ui.UI;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.Entity;
import dev.codewizz.world.GameObject;

public class SelectPanel extends Panel {

    private final static Environment ENVIRONMENT = new Environment();
    private final static DirectionalLight DIRECTIONAL_LIGHT = new DirectionalLight().set(1, 1, 1, -1, -1, -1);
    private final static PerspectiveCamera PERSPECTIVE_CAMERA = new PerspectiveCamera(45, 128, 128);
    private final static FrameBuffer FBO = new FrameBuffer(Pixmap.Format.RGBA8888,128,128,true);
    private final static ModelBatch MODEL_BATCH = new ModelBatch();
    static {
        ENVIRONMENT.add(DIRECTIONAL_LIGHT);

        PERSPECTIVE_CAMERA.position.set(2, 2, 2);
        PERSPECTIVE_CAMERA.lookAt(0, 1, 0);
        PERSPECTIVE_CAMERA.near = 0.1f;
        PERSPECTIVE_CAMERA.far = 100f;
        PERSPECTIVE_CAMERA.update();
    }

    private final GameObject target;

    private Label name;
    private Label description;
    private Label health;
    private Image image;

    private Table buttons;

    public SelectPanel(GameObject target) {
        super(Assets.findSprite("select-menu"));
        this.target = target;
        target.addButtonsToSelectMenu(target, buttons);
    }

    private void updateData() {
        if (target instanceof Entity) {
            name.setText(target.getName());
            description.setText(target.getDescription());
            health.setText("Health");
            image.setDrawable(new TextureRegionDrawable(createPreview(((Entity) target).getInstance())));
        } else {
            name.setText(target.getName());
            description.setText(target.getDescription());
            health.setText("");
        }
    }

    @Override
    public void update() {
        Vector3 screenPos = Main.instance.getRenderer().getCamera().getPerspectiveCamera().project(new Vector3(target.getPosition()));
        setPosition(screenPos.x - content.getWidth() / 2f, screenPos.y);

        updateData();
    }

    @Override
    void setup() {
        name = new Label("", UI.skin);
        description = new Label("", UI.skin);
        description.setWrap(true);
        health = new Label("", UI.skin);
        image = new Image(Assets.findSprite("select-menu-head"));


        Table left = new Table();
        Table right = new Table();

        left.add(image).size(48 * UI.SCALE);
        content.add(left).pad(3 *  UI.SCALE, 3 * UI.SCALE, 3 * UI.SCALE, 0).size(48 * UI.SCALE).left();

        right.add(name).expandX().fillX().left().row();
        right.add(description).expandX().fillX().left().row();
        right.add(health).expandX().fillX().left().row();

        buttons = new Table();
        content.add(right).expand().fill().right();
        content.add(buttons).size(22 * UI.SCALE, 54 * UI.SCALE).right();
    }

    public TextureRegion createPreview(ModelInstance inst) {
        ModelInstance instance = new ModelInstance(inst);
        instance.transform.idt();
        instance.transform.rotate(Vector3.Y, 270);

        FBO.begin();

        Gdx.gl.glViewport(0, 0, 128, 128);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        MODEL_BATCH.begin(PERSPECTIVE_CAMERA);
        MODEL_BATCH.render(instance, ENVIRONMENT);
        MODEL_BATCH.end();

        FBO.end();

        Texture texture = FBO.getColorBufferTexture();

        TextureRegion region = new TextureRegion(texture);
        region.flip(false, true);

        return region;
    }
}
