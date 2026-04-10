package dev.codewizz.input.result;

import com.badlogic.gdx.math.Vector3;

public interface PickAreaListener {

    void handle(Vector3 min, Vector3 max);

}
