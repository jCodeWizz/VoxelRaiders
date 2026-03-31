package dev.codewizz.utils;

import java.util.Random;

public class WUtils {

    public static final Random RANDOM = new Random();

    public static int getRandom(int min, int max) {
        if (min == max) { return min; }

        return RANDOM.nextInt(max - min) + min;
    }
}
