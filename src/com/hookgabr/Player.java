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
        rollStats();
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

    public void addItem(Item item) {
        itemList.add(item);
        System.out.println("DEBUG: ADDED " + item.name);
    }

    public void removeItem(int index) {
        itemList.remove(index);
        System.out.println("DEBUG: REMOVED ALL ITEMS");
    }

    public void removeAllItems() {
        for (int i = 0; i < itemList.size() - 1; i++) {
            itemList.remove(i);
        }
    }

    public void printItemList() {
        System.out.println("--------------------\nITEMS:");
        for (int i = 0; i < itemList.size(); i++) {
            String items = String.format("%d: %s", i + 1, itemList.get(i).name);
            System.out.println(items);
        }
        System.out.println("--------------------");
    }

    public void equipWeapon(Weapon weapon) {
        equippedWeapon = weapon;
        statAttack.val = statBaseAttack.val + weapon.getDamageRating();
        System.out.println("--------------------\nEQUIPPED " + weapon.name + " ATTACK + " + weapon.getDamageRating() + "\n--------------------");
    }

    public void equipArmor(Armor armor) {
        equippedArmor = armor;
        statDefense.val = statBaseDefense.val + armor.getDefenseRating();
        System.out.println("--------------------\nEQUIPPED " + armor.name + " DEFENSE + " + armor.getDefenseRating() + "\n--------------------");
    }

    public void printSpellList() {
        System.out.println("SPELLS:\n--------------------");
        for (int i = 0; i < spellList.size(); i++) {
            String spells = String.format("%d: %s", i + 1, spellList.get(i).name);
            System.out.println(spells);
        }
        System.out.println("--------------------");
    }
}
