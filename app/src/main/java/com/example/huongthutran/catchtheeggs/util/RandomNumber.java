package com.example.huongthutran.catchtheeggs.util;

import java.util.Random;

/**
 * Created by Huong Thu Tran on 4/20/2018.
 */

public class RandomNumber {
    private static Random rand = new Random();

    public static int getRandIntBetween(int lowerBound, int upperBound) {
        return rand.nextInt(upperBound - lowerBound) + lowerBound;
    }

    public static int getRandInt(int upperBound) {
        return rand.nextInt(upperBound);
    }
}
