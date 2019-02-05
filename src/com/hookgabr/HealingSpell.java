package com.hookgabr;

public class HealingSpell extends Spell {

    public int healing = 0;

    public HealingSpell() { }

    public HealingSpell(String name, int mpCost, int healing) {
        this.name = name;
        this.mpCost = mpCost;
        this.healing = healing;
    }
}
