package dev.codewizz.gfx.gui.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import dev.codewizz.gfx.gui.UI;
import dev.codewizz.gfx.gui.elements.UIIconButton;
import dev.codewizz.gfx.gui.elements.UIImageButton;
import dev.codewizz.gfx.gui.elements.UILabel;
import dev.codewizz.input.MouseInput;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.GameObjectInfo;
import dev.codewizz.world.GameObjectInfoShop;
import dev.codewizz.world.inventory.Item;
import dev.codewizz.world.objects.Cow;
import dev.codewizz.world.objects.IBuy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectMenu extends Menu {

    public static final String ID = "object";

    public static List<GameObjectInfoShop> objects = new ArrayList<>();
    public static GameObjectInfoShop selected;

    static {
        //objects.add(new Cow());
        //objects.add(new Cow());
        //objects.add(new Cow());
    }

    private Table scrollTable;
    private ScrollPane scrollPane;

    @Override
    protected void setup() {
        Table main = new Table();
        main.setBackground(new SpriteDrawable(Assets.getSprite("object-menu")));

        Table top = new Table();
        Table categories = new Table();

        scrollTable = new Table();
        scrollPane = new ScrollPane(scrollTable);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setOverscroll(false, true);
        scrollPane.setScrollbarsVisible(true);
        fillScrollTable(objects);

        Table view = new Table();

        base.add(main).size(148 * UI.SCALE, 328 * UI.SCALE).expand().left().padLeft(10 * UI.SCALE);

        main.add(top).expand().size(148 * UI.SCALE, 25*UI.SCALE).row();
        main.add(categories).expand().size(148 * UI.SCALE, 26*UI.SCALE).row();
        main.add(scrollPane).expand().size(148 * UI.SCALE, 144*UI.SCALE).row();
        main.add(view).expand().size(148 * UI.SCALE, 133*UI.SCALE).row();

        top.add(UILabel.create("Build Object", UILabel.defaultStyle)).expand().left().padLeft(5 * UI.SCALE);
        top.add(closeButton()).expand().right().size(22 * UI.SCALE, 24 * UI.SCALE).pad(UI.SCALE, 0, 0, UI.SCALE);

        scrollPane.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                UI.stage.setKeyboardFocus(scrollPane);
                UI.stage.setScrollFocus(scrollPane);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                UI.stage.setKeyboardFocus(null);
                UI.stage.setScrollFocus(null);
            }

        });
    }

    public void fillScrollTable(List<GameObjectInfoShop> objects) {
        scrollTable.clear();
        scrollTable.top();

        int i = 0;
        for (GameObjectInfoShop o : objects) {
            i++;
            ImageButton button = UIImageButton.create(UIImageButton.buySlotStyle, spriteFromModel(o.getModel()));
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    selected = o;
                }
            });

            button.addListener(new InputListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    //TODO: HOVER
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    //TODO: HOVER
                }
            });

            scrollTable.add(button).size(48 * UI.SCALE, 48 * UI.SCALE).top();

            if (i % 3 == 0) {
                scrollTable.row();
            }
        }
    }

    public Sprite spriteFromModel(Model model) {
        ModelBatch iconModelBatch = new ModelBatch();

        int size = 256;

        FrameBuffer frameBuffer = new FrameBuffer(
            Pixmap.Format.RGBA8888,
            size,
            size,
            true
        );

        ModelInstance instance = new ModelInstance(model);
        instance.transform.rotate(Vector3.Y, 180);

        // Find model bounds
        BoundingBox bounds = new BoundingBox();
        instance.calculateBoundingBox(bounds);

        Vector3 center = new Vector3();
        Vector3 dimensions = new Vector3();

        bounds.getCenter(center);
        bounds.getDimensions(dimensions);

        float maxDimension = Math.max(
            dimensions.x,
            Math.max(dimensions.y, dimensions.z)
        );

        // Orthographic camera = nice "isometric icon" look
        OrthographicCamera camera = new OrthographicCamera();

        float viewSize = maxDimension * 1.5f;

        camera.viewportWidth = viewSize;
        camera.viewportHeight = viewSize;

        // Isometric-ish direction
        camera.position.set(
            center.x + maxDimension,
            center.y + maxDimension,
            center.z + maxDimension
        );

        camera.lookAt(center);
        camera.up.set(Vector3.Y);
        camera.near = 0.01f;
        camera.far = maxDimension * 10f;

        camera.update();

        Environment environment = new Environment();

        environment.set(
            new ColorAttribute(
                ColorAttribute.AmbientLight,
                0.8f,
                0.8f,
                0.8f,
                1f
            )
        );

        environment.add(
            new DirectionalLight().set(
                0.8f,
                0.8f,
                0.8f,
                -1f,
                -0.8f,
                -0.5f
            )
        );

        frameBuffer.begin();

        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(
            GL20.GL_COLOR_BUFFER_BIT |
                GL20.GL_DEPTH_BUFFER_BIT
        );

        iconModelBatch.begin(camera);
        iconModelBatch.render(instance, environment);
        iconModelBatch.end();

        frameBuffer.end();

        Texture texture = frameBuffer.getColorBufferTexture();

        // FrameBuffers are vertically flipped in LibGDX/OpenGL
        TextureRegion region = new TextureRegion(texture);
        region.flip(false, true);

        Sprite sprite = new Sprite(region);

        return sprite;
    }
}
