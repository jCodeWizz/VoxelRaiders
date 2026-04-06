package dev.codewizz.world.objects;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.WUtils;
import dev.codewizz.world.Entity;
import dev.codewizz.world.objects.behaviour.TaskTemplate;
import dev.codewizz.world.objects.behaviour.templates.IdleWaitTemplate;
import dev.codewizz.world.objects.behaviour.templates.WanderTemplate;

public class Cow extends Entity {

    private final static WanderTemplate WANDER = new WanderTemplate();
    private final static IdleWaitTemplate WAIT = new IdleWaitTemplate(2f, 5f);

    public Cow() {
        super("vxr:cow");

        instance = new ModelInstance(Assets.findModel(getId()));
    }

    @Override
    public TaskTemplate findNewTask() {
        int type = WUtils.getRandom(0, 2);

        if (type == 0) {
            return WANDER;
        } else {
            return WAIT;
        }
    }
}
