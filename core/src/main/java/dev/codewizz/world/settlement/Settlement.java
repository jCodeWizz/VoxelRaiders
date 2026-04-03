package dev.codewizz.world.settlement;

import com.badlogic.gdx.math.Vector3;
import dev.codewizz.world.objects.Hermit;
import java.util.ArrayList;
import java.util.List;

public class Settlement {

    private final Vector3 position;

    private final List<Hermit> members;

    public Settlement(Vector3 position) {
        this.position = new Vector3(position);
        this.members = new ArrayList<>();
    }

    public Vector3 getPosition() {
        return position;
    }

    public List<Hermit> getMembers() {
        return members;
    }
}
