package dev.codewizz.world.settlement;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Queue;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.World;
import dev.codewizz.world.inventory.Item;
import dev.codewizz.world.objects.Hermit;
import dev.codewizz.world.objects.Storage;
import dev.codewizz.world.objects.behaviour.TaskTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Settlement {

    private final Vector3 position;
    private final World world;

    private final Queue<TaskTemplate> tasks;
    private final List<Hermit> members;
    private final List<Storage> storages;

    public Settlement(World world, Vector3 position) {
        this.world = world;
        this.position = new Vector3(position);
        this.tasks = new Queue<>();
        this.members = new ArrayList<>();
        this.storages = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Hermit hermit = new Hermit();
            hermit.getPosition().set(position.x, position.y + hermit.getSize().y / 2f, position.z);
            addMember(hermit);
        }
    }

    public Queue<TaskTemplate> getTasks() {
        return tasks;
    }

    public Storage findStorage(Hermit hermit) {
        List<Storage> storages = new ArrayList<>(getStorages());
        storages.sort((o1, o2) -> compare(o1, o2, hermit));

        for (Storage storage : storages) {
            if (storage.accepts(hermit.getInventory().getItems().values())) {
                return storage;
            }
        }

        return null;
    }

    private int compare(Storage s1, Storage s2, Hermit h) {
        float dst1 = h.getPosition().dst2(s1.getPosition());
        float dst2 = h.getPosition().dst2(s2.getPosition());
        return Float.compare(dst1, dst2);
    }

    public void addTask(TaskTemplate task) {
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

    public void addStorage(Storage storage) {
        storages.add(storage);
    }

    public List<Storage> getStorages() {
        return storages;
    }
}
