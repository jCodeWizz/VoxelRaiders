package dev.codewizz.gfx.gui.layers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import dev.codewizz.gfx.gui.UI;
import dev.codewizz.gfx.gui.elements.UIIconButton;
import dev.codewizz.gfx.gui.elements.UITextTooltip;
import dev.codewizz.gfx.gui.elements.UIToggle;
import dev.codewizz.gfx.gui.menus.AreaMenu;
import dev.codewizz.gfx.gui.menus.ConsoleMenu;
import dev.codewizz.gfx.gui.menus.NotificationMenu;
import dev.codewizz.main.Main;
import dev.codewizz.gfx.gui.menus.ObjectMenu;
import dev.codewizz.utils.Assets;

public class GameLayer extends Layer {

    public Table main;
    private UIIconButton constructionMenuButton;
    private UIIconButton toolMenuButton;
    private UIIconButton areaMenuButton;

    public UIToggle speed0;
    public UIToggle speed1;
    public UIToggle speed2;
    public UIToggle speed3;

    @Override
    public void open(Stage stage) {
        setup();

        menus.put(NotificationMenu.ID, new NotificationMenu());
        menus.put(ConsoleMenu.ID, new ConsoleMenu());
        menus.put(AreaMenu.ID, new AreaMenu(areaMenuButton));
        menus.put(ObjectMenu.ID, new ObjectMenu());

        menus.get(NotificationMenu.ID).open();
    }

    @Override
    public void close(Stage stage) {

    }

    private void setup() {
        Table backGround = new Table();
        backGround.setFillParent(true);
        UI.stage.addActor(backGround);
        Table backGroundImage = new Table();
        backGroundImage.setBackground(
                new Image(Assets.getSprite("icon-board-extension")).getDrawable());
        backGround.add(backGroundImage).expand().width(Gdx.graphics.getWidth() + 100).height(11 * UI.SCALE).bottom().right();

        main = new Table();
        main.setFillParent(true);
        UI.stage.addActor(main);

        Table board = new Table();
        main.add(board).expand().size(146 * UI.SCALE, 30 * UI.SCALE).bottom();
        board.setBackground(new Image(Assets.getSprite("icon-board")).getDrawable());

        UIIconButton settlementIcon = UIIconButton.create("manage-icon");
        settlementIcon.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //openMenu(settlementMenu);
            }
        });
        settlementIcon.addListener(UITextTooltip.create("Settlement (Y)"));

        areaMenuButton = UIIconButton.create("area-icon");
        areaMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                openMenu(AreaMenu.ID);
            }
        });
        areaMenuButton.addListener(UITextTooltip.create("Areas"));


        constructionMenuButton = UIIconButton.create("build-icon");
        constructionMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                openMenu("object");
            }
        });
        constructionMenuButton.addListener(UITextTooltip.create("Construction"));

        UIIconButton peopleIcon = UIIconButton.create("people-icon");
        peopleIcon.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //openMenu(peopleMenu);
            }
        });
        peopleIcon.addListener(UITextTooltip.create("Hermits (C)"));

        toolMenuButton = UIIconButton.create("tool-icon");
        toolMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //openMenu(toolMenu);
            }
        });
        toolMenuButton.addListener(UITextTooltip.create("Tools"));

        int bottomPad = 6 * UI.SCALE;

        board.add(settlementIcon).size(22 * UI.SCALE, 24 * UI.SCALE)
                .pad(0, 0, bottomPad, 3 * UI.SCALE);
        board.add(areaMenuButton).size(22 * UI.SCALE, 24 * UI.SCALE)
                .pad(0, 3 * UI.SCALE, bottomPad, 3 * UI.SCALE);
        board.add(constructionMenuButton).size(22 * UI.SCALE, 24 * UI.SCALE)
                .pad(0, 3 * UI.SCALE, bottomPad, 3 * UI.SCALE);
        board.add(peopleIcon).size(22 * UI.SCALE, 24 * UI.SCALE)
                .pad(0, 3 * UI.SCALE, bottomPad, 3 * UI.SCALE);
        board.add(toolMenuButton).size(22 * UI.SCALE, 24 * UI.SCALE)
                .pad(0, 3 * UI.SCALE, bottomPad, 0);

        Table bottomRightTable = new Table();
        bottomRightTable.bottom().right();
        bottomRightTable.setFillParent(true);

        speed0 = UIToggle.create(UIToggle.speed0Style);
        speed0.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.gameSpeed = 0;
            }
        });
        speed0.addListener(UITextTooltip.create("Pause (0)"));

        speed1 = UIToggle.create(UIToggle.speed1Style);
        speed1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.gameSpeed = 1;
            }
        });
        speed1.addListener(UITextTooltip.create("x1 (1)"));

        speed2 = UIToggle.create(UIToggle.speed2Style);
        speed2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.gameSpeed = 3;
            }
        });
        speed2.addListener(UITextTooltip.create("x3 (2)"));

        speed3 = UIToggle.create(UIToggle.speed3Style);
        speed3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.gameSpeed = 5;
            }
        });
        speed3.addListener(UITextTooltip.create("x5 (3)"));


        ButtonGroup<UIToggle> buttonGroup = new ButtonGroup<>();
        buttonGroup.add(speed1);
        buttonGroup.add(speed0);
        buttonGroup.add(speed2);
        buttonGroup.add(speed3);

        bottomRightTable.add(speed0).size(9 * UI.SCALE, 10 * UI.SCALE).pad(0, 2 * UI.SCALE, 5 * UI.SCALE, 0);
        bottomRightTable.add(speed1).size(9 * UI.SCALE, 10 * UI.SCALE).pad(0, 2 * UI.SCALE, 5 * UI.SCALE, 0);
        bottomRightTable.add(speed2).size(15 * UI.SCALE, 10 * UI.SCALE).pad(0, 2 * UI.SCALE, 5 * UI.SCALE, 0);
        bottomRightTable.add(speed3).size(21 * UI.SCALE, 10 * UI.SCALE).pad(0, 2 * UI.SCALE, 5 * UI.SCALE, 20 * UI.SCALE);

        UI.stage.addActor(bottomRightTable);
    }
}
