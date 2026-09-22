package dev.codewizz.utils;

import java.util.Random;

public class WUtils {

    public static final Random RANDOM = new Random();

    public static int getRandomInBounds(int bounds, int safe) {
        if (safe >= bounds) {
            Logger.error("safe is lower than bounds");
        }

        int range = bounds - safe;

        if (RANDOM.nextBoolean()) {
            return -(safe + 1 + RANDOM.nextInt(range));
        } else {
            return safe + 1 + RANDOM.nextInt(range);
        }
    }

    public static int getRandom(int min, int max) {
        if (min == max) { return min; }

        return RANDOM.nextInt(max - min) + min;
    }
}
