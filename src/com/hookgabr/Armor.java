package com.hookgabr;

public class Armor extends Item {

    private int defenseRating = 0;

    public int getDefenseRating() {
        return defenseRating;
    }

    public void setDefenseRating(int defense) {
        defenseRating = defense;
    }

    public Armor() { }

    public Armor(String name, int value, int defense) {
        this.name = name;
        this.value = value;
        this.defenseRating = defense;
    }
}
