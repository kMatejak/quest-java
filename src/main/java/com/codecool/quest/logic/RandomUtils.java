package com.codecool.quest.logic;

import java.util.Random;

public class RandomUtils {

    private static Random random = new Random();

    public static int getPositiveOrNegative() {
        int[] list = {1, -1};
        return random.nextInt(list.length); // return list[index]
    }

    public static int getRandomCoordinate(int start, int stop) {
        return random.nextInt((stop + 1) - start) + start;
    }

}