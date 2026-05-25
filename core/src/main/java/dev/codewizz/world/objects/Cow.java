package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.WUtils;
import dev.codewizz.world.Entity;
import dev.codewizz.world.objects.behaviour.TaskTemplate;
import dev.codewizz.world.objects.behaviour.templates.GrazeTemplate;
import dev.codewizz.world.objects.behaviour.templates.IdleWaitTemplate;
import dev.codewizz.world.objects.behaviour.templates.WanderTemplate;

public class Cow extends Entity {

    private final static WanderTemplate WANDER = new WanderTemplate();
    private final static IdleWaitTemplate WAIT = new IdleWaitTemplate(2f, 5f);
    private final static GrazeTemplate GRAZE = new GrazeTemplate();

    public Cow() {
        super("vxr:cow");

        this.name = "Cow";
        this.description = "Likes to Mooove it Mooove it";

        instance = new ModelInstance(Assets.findModel(getId()));
        animationController = new AnimationController(instance);
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        animationController.update(dt);
    }

    @Override
    public TaskTemplate findNewTask() {
        int type = WUtils.getRandom(0, 3);

        if (type == 0) {
            return WAIT;
        } else if (type == 1) {
            return GRAZE;
        } else {
            return WANDER;
        }
    }
}
