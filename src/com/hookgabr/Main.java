package com.hookgabr;

/**
 * This is the class where the code is executed
 */
public class Main {

    /**
     * This method is where everything is executed when the program is ran.
     */
    public static void main(String[] args) {
        /*
        Player hero1 = new Player("Hero 1", 5);
        Weapon sword = new Weapon("sword", 10, 4);
        Armor iron = new Armor("iron armor", 10, 2);
        hero1.itemList.add(sword);
        hero1.itemList.add(iron);
        DamageSpell fireball = new DamageSpell("fireball", 3, 5);
        HealingSpell heal = new HealingSpell("heal", 4, 3);
        BuffSpell bulk = new BuffSpell("bulk", 3, 2);
        hero1.spellList.add(fireball);
        hero1.spellList.add(heal);
        hero1.spellList.add(bulk);
        Monster slime = new Monster("Slime", 3, 3, 2, 5);
        slime.spellList.add(fireball);
        Battle battle = new Battle(hero1, slime);
        */
        Battle.getQueuedMonster();
    }
}
// TODO: Create Item, Weapon, and Armor class and implement to Character.
// TODO: Create Spell class and implement to Character.
// TODO: Create Battle class.
// TODO: Create Store class.
// TODO: Create game progression system.
// TODO: Graphics?
