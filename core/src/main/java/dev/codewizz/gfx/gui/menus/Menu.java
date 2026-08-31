package dev.codewizz.gfx.gui.menus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import dev.codewizz.gfx.gui.UI;
import dev.codewizz.world.GameObject;

public abstract class Menu {

    private boolean open = true;

    protected boolean shouldClose = true;

    protected Table base;

    public Menu() {
        base = new Table();
        base.setFillParent(true);
        UI.stage.addActor(base);

        setup();
        close();
    }

    protected abstract void setup();

    public void close() {
        open = false;
        base.setVisible(false);
        onClose();
    }

    public void open() {
        open = true;
        base.setVisible(true);
        onOpen();
    }

    public void toggle() {
        if (open) { close(); } else { open(); }
    }

    protected TextureRegionDrawable createBackground(float alpha) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(0, 0, 0, alpha));
        pixmap.fill();

        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        return new TextureRegionDrawable(new TextureRegion(texture));
    }

    public boolean isOpen() {
        return open;
    }

    public void onOpen() {

    }

    public void onClose() {

    }

    public void clickedOn(GameObject object) {

    }

    public void render(SpriteBatch b) {

    }

    public boolean shouldClose() {
        return shouldClose;
    }
}
