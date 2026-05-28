package dev.codewizz.world.objects.behaviour.jobs;

import com.badlogic.gdx.graphics.Color;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.main.Main;
import dev.codewizz.world.objects.Hermit;
import dev.codewizz.world.objects.behaviour.TaskTemplate;

public abstract class Job {

    private final Hermit hermit;

    public Job(Hermit hermit) {
        this.hermit = hermit;
    }

    public TaskTemplate findNewTask() {
        for (TaskTemplate t : Main.instance.getWorld().getSettlement().getTasks()) {
            if (t.canTake(hermit)) {
                Main.instance.getWorld().getSettlement().getTasks().removeValue(t, false);
                return t;
            }
        }

        return null;
    }
    public void render(Renderer renderer) {

    }
}
