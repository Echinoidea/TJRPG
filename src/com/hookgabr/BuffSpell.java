package com.hookgabr;

public class BuffSpell extends Spell {

    private int buff = 0;

    public int getBuff() {
        return buff;
    }

    public void setBuff(int buff) {
        this.buff = buff;
    }

    public BuffSpell() { }

    public BuffSpell(String name, int mpCost, int buff) {
        this.name = name;
        this.mpCost = mpCost;
        this.buff = buff;
    }
}
