package com.hookgabr;

public class Monster extends Character {

    public int expYield = 0;
    public int goldYeild = 0;
    public int lootChance = 0;

    public Monster(String name, int level, int expYield, int goldYield, int lootChance) {
        this.name = name;
        rollStats();

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
