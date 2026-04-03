package dev.codewizz.world.objects;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.decorator.Repeat;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.Entity;

public class Hermit extends Entity {

    public Hermit() {
        super("vxr:hermit");

        instance = new ModelInstance(Assets.findModel(getId())); //TODO: based on job

        behaviour = new BehaviorTree<>(
            new Repeat<>(
                
            ),
            this
        );
    }
}
