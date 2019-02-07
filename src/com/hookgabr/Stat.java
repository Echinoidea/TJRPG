package com.hookgabr;

import java.util.Random;

public class Stat {

    // Variable declaration

    public String name;
    int max = 0;
    int val;

    void addVal() {
        Random rand = new Random();
        int rVal = rand.nextInt(5) + 2;
        if (this.val + rVal > 100) {
            this.val = 100;
        }
        else {
            this.val += rVal;
        }
    }

    /**
     * Constructor
     * @param name The name of this Stat
     * @param val The current value of this Stat
     */
    Stat(String name, int val) {
        this.name = name;
        this.val = val < max ? val : max;
    }
}