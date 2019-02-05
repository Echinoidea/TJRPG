package com.hookgabr;

public class BuffSpell extends Spell {

    public int buff = 0;

    public BuffSpell() { }

    public BuffSpell(String name, int mpCost, int buff) {
        this.name = name;
        this.mpCost = mpCost;
        this.buff = buff;
    }
}
