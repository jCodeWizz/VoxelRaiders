package dev.codewizz.world.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.branch.RandomSelector;
import com.badlogic.gdx.ai.btree.branch.Selector;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.badlogic.gdx.ai.btree.decorator.Repeat;
import com.badlogic.gdx.ai.btree.leaf.Wait;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.ai.utils.random.UniformFloatDistribution;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import dev.codewizz.gfx.Renderer;
import dev.codewizz.utils.Assets;
import dev.codewizz.world.Entity;
import dev.codewizz.world.objects.behaviour.ChangeColourLeaf;
import dev.codewizz.world.objects.behaviour.FollowPathTask;
import dev.codewizz.world.objects.behaviour.SetGoalLeaf;
import dev.codewizz.world.objects.behaviour.SetPathLeaf;
import dev.codewizz.world.objects.behaviour.pathfinding.NavCell;
import net.mgsx.gltf.loaders.gltf.GLTFLoader;
import net.mgsx.gltf.scene3d.scene.SceneAsset;

public class Cow extends Entity {

    public Cow() {
        super("vxr:cow");

        instance = new ModelInstance(Assets.findModel(getId()));

        behaviour = new BehaviorTree<>(
            new Repeat<>(
                new Sequence<>(
                    new Wait<>(new UniformFloatDistribution(2f, 5f)),
                    new RandomSelector<>(
                        new Sequence<>( // eating
                            new ChangeColourLeaf(Color.BLUE),
                            new Wait<>(new UniformFloatDistribution(2f, 5f)),
                            new ChangeColourLeaf(Color.GREEN)
                        ),
                        new Sequence<>( // idle
                            new ChangeColourLeaf(Color.RED),
                            new Wait<>(new UniformFloatDistribution(2f, 5f)),
                            new ChangeColourLeaf(Color.GREEN)
                        ),
                        new Sequence<>( // wander
                            new ChangeColourLeaf(Color.YELLOW),
                            new SetGoalLeaf(),
                            new SetPathLeaf(),
                            new FollowPathTask(),
                            new ChangeColourLeaf(Color.GREEN)
                        )
                    )
                )
            ),
            this
        );
    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderObjectInstance(this, instance);
    }
}
