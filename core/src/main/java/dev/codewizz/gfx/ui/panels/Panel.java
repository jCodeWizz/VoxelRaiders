package dev.codewizz.gfx.ui.panels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import dev.codewizz.gfx.ui.UI;
import dev.codewizz.main.Main;
import dev.codewizz.utils.Assets;

public abstract class Panel extends Table {

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

    protected Table content;

    private final Stack stack;

    public Panel(Sprite background) {
        this.stack = new Stack();
        this.content = new Table();

        float width = background.getWidth() * UI.SCALE;
        float height = background.getHeight() * UI.SCALE;

        setSize(width, height);

        Table backGroundTable = new Table();
        backGroundTable.add(new Image(background)).expand().fill();

        stack.add(backGroundTable);

        setup();

        stack.add(content);
        add(stack).size(width, height);
    }

    public void update() {}

    abstract void setup();

    public void open() {

    }

    protected ImageButton closeIcon() {
        ImageButton.ImageButtonStyle style =
            new ImageButton.ImageButtonStyle(UI.skin.get("stretch", ImageButton.ImageButtonStyle.class));

        style.imageUp = new SpriteDrawable(Assets.findSprite("close-icon"));

        ImageButton button = new ImageButton(style);
        button.getImageCell().expand().fill();

        return button;
    }

    protected TextureRegion createPreview(ModelInstance inst) {
        ModelInstance instance = new ModelInstance(inst);
        instance.transform.idt();
        instance.transform.rotate(Vector3.Y, 270);

        return modelToImage(instance);
    }

    protected TextureRegion createPreview(Model model) {
        return modelToImage(new ModelInstance(model));
    }

    private TextureRegion modelToImage(ModelInstance instance) {
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

    public void close() {
        Main.instance.getRenderer().getUI().close(this);
    }
}
