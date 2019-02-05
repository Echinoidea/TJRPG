package com.hookgabr;

public class Weapon extends Item {

    public int damageRating = 0;

    public int getDamageRating() {
        return damageRating;
    }

    public void setDamageRating(int damage) {
        damageRating = damage;
    }

    public Weapon() { }

    public Weapon(String name, int value, int damage) {
        this.name = name;
        this.value = value;
        this.damageRating = damage;
    }
}
