package dev.codewizz.world.settlement;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Queue;
import dev.codewizz.world.Entity;
import dev.codewizz.world.World;
import dev.codewizz.world.objects.Hermit;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Settlement {

    private final Vector3 position;
    private final World world;

    private final Queue<BehaviorTree<Entity>> tasks;
    private final List<Hermit> members;

    public Settlement(World world, Vector3 position) {
        this.world = world;
        this.position = new Vector3(position);
        tasks = new Queue<>();
        this.members = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Hermit hermit = new Hermit();
            hermit.getPosition().set(position.x, position.y + hermit.getSize().y / 2f, position.z);
            addMember(hermit);
        }
    }

    public Queue<BehaviorTree<Entity>> getTasks() {
        return tasks;
    }

    public void addTask(BehaviorTree<Entity> task) {
        tasks.addLast(task);
    }

    public void addMember(Hermit hermit) {
        members.add(hermit);
        world.addObject(hermit);
    }

    public Vector3 getPosition() {
        return position;
    }

    public List<Hermit> getMembers() {
        return members;
    }
}
