package dev.codewizz.world.settlement;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Queue;
import dev.codewizz.gfx.gui.menus.NotificationMenu;
import dev.codewizz.utils.Assets;
import dev.codewizz.utils.WUtils;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.World;
import dev.codewizz.world.inventory.Inventory;
import dev.codewizz.world.objects.Hermit;
import dev.codewizz.world.objects.Storage;
import dev.codewizz.world.objects.behaviour.TaskTemplate;
import java.util.ArrayList;
import java.util.List;

public class Settlement {

    private final Vector3 position;
    private final World world;

    private final Queue<TaskTemplate> tasks;
    private final List<Hermit> members;
    private final Inventory inventory;
    private final List<GameObject> stations;

    public Settlement(World world, Vector3 position) {
        this.world = world;
        this.position = new Vector3(position);

        this.tasks = new Queue<>();
        this.members = new ArrayList<>();
        this.inventory = new Inventory();
        this.stations = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Hermit hermit = new Hermit();
            hermit.getPosition().set(position.x + WUtils.getRandomInBounds(3, 1), position.y, position.z + WUtils.getRandomInBounds(3, 1));
            addMember(hermit);
        }
    }

    public Queue<TaskTemplate> getTasks() {
        return tasks;
    }

    private int compare(Storage s1, Storage s2, Hermit h) {
        float dst1 = h.getPosition().dst2(s1.getPosition());
        float dst2 = h.getPosition().dst2(s2.getPosition());
        return Float.compare(dst1, dst2);
    }

    public void addTask(TaskTemplate task) {
        tasks.addLast(task);
    }

    public void addTasks(List<TaskTemplate> tasks) {
        for (TaskTemplate taskTemplate : tasks) {
            addTask(taskTemplate);
        }
    }

    public void addMember(Hermit hermit) {
        members.add(hermit);
        world.addObject(hermit);
        NotificationMenu.makeNotification(Assets.getSprite("people-icon"), "A new Hermit arrived!",  "Please welcome " + hermit.getName() + " to your settlement");
    }

    public Vector3 getPosition() {
        return position;
    }

    public List<Hermit> getMembers() {
        return members;
    }

    public void addStation(GameObject station) {
        stations.add(station);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public List<GameObject> getStations() {
        return stations;
    }

    public void removeStation(GameObject station) {
        stations.remove(station);
    }
}
