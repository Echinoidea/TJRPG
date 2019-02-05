package com.hookgabr;

public class DamageSpell extends Spell {

    public int damage = 0;

    public DamageSpell() { }

    public DamageSpell(String name, int mpCost, int damage) {
        this.name = name;
        this.mpCost = mpCost;
        this.damage = damage;
    }
}
