package com.hookgabr;

import java.util.*;

public class Player extends Character {

    public int exp = 0;
    public int expToLevel = 0;
    public int gold = 0;

    public List<Item> itemList = new ArrayList<Item>();

    public Player() { }

    public Player(String name, int level) {
        Random rand = new Random();
        this.name = name;
        rollAllStats();
        gold = rand.nextInt(11 * 10);
        for (int i = 1; i < level; i++) {
            levelUp();
        }

        healFull();
        printStats();
    }

    public boolean canLevelUp() {
        return exp >= expToLevel;
    }

    public void printItemList() {
        System.out.println("\n--------------------\n> Items:");
        for (int i = 0; i < itemList.size(); i++) {
            String items = String.format("%d: %s", i + 1, itemList.get(i).name);
            System.out.println(items);
        }
        System.out.println("--------------------");
    }

    public void equipWeapon(Weapon weapon) {
        equippedWeapon = weapon;
        statAttack.val = statBaseAttack.val + weapon.getDamageRating();
        System.out.println("\n> Equipped " + weapon.name + ".");
    }

    public void equipArmor(Armor armor) {
        equippedArmor = armor;
        statDefense.val = statBaseDefense.val + armor.defenseRating;
        System.out.println("\n> Equipped " + armor.name + ".");
    }

    public void printSpellList() {
        System.out.println("\n--------------------\n> Spells:");
        for (int i = 0; i < spellList.size(); i++) {
            String spells = String.format("%d: %s", i + 1, spellList.get(i).name);
            System.out.println(spells);
        }
        System.out.println("--------------------");
    }
}
