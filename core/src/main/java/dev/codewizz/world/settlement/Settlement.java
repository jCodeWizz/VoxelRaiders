package dev.codewizz.world.settlement;

import com.badlogic.gdx.math.Vector3;
import dev.codewizz.world.World;
import dev.codewizz.world.objects.Hermit;
import java.util.ArrayList;
import java.util.List;

public class Settlement {

    private final Vector3 position;
    private final World world;

    private final List<Hermit> members;

    public Settlement(World world, Vector3 position) {
        this.world = world;
        this.position = new Vector3(position);
        this.members = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Hermit hermit = new Hermit();
            hermit.getPosition().set(position.x, position.y + 0.9f, position.z);
            addMember(hermit);
        }
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
