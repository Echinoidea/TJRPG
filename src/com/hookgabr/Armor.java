package com.hookgabr;

public class Armor extends Item {

    public int defenseRating = 0;

    public Armor(String name, int value, int defense) {
        this.name = name;
        this.value = value;
        this.defenseRating = defense;
    }
}
