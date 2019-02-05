package com.hookgabr;

import java.util.Random;

public class Stat {

    // Variable declaration

    public String name = null;
    public int max = 0;
    public int val = 0;

    public void addVal() {
        Random rand = new Random();
        int rVal = rand.nextInt(5) + 2;
        if (this.val + rVal > 100) {
            this.val = 100;
        }
        else {
            this.val += rVal;
        }
    }

    // Constructors

    /**
     * Default constructor
     */
    public Stat() { }

    public Stat(String name, int max, int val) {
        this.name = name;
        this.max = max;
        this.val = val;
    }

    /**
     * Constructor
     * @param name The name of this Stat
     * @param val The current value of this Stat
     */
    public Stat(String name, int val) {
        this.name = name;
        this.val = val < max ? val : max;
    }
}