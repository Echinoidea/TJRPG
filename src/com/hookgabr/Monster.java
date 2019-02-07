package com.hookgabr;

public class Monster extends Character {

    int expYield = 0;
    int goldYeild = 0;
    private int lootChance = 0;

    Monster(String name, int level, int expYield, int goldYield, int lootChance) {
        this.name = name;
        rollAllStats();

        this.expYield = expYield;
        this.goldYeild = goldYield;
        this.lootChance = lootChance;

        for (int i = 1; i < level; i++) {
            levelUp();
        }

        healFull();
        printStats();
    }
}
