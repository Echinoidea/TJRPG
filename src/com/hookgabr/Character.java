package com.hookgabr;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Character {

    public String name = null;

    int level = 1;

    Stat statStr = new Stat("Strength", 1);
    Stat statWis = new Stat("Wisdom", 1);
    Stat statEnd = new Stat("Endurance", 1);
    Stat statDex = new Stat("Dexterity", 1);
    Stat statLuc = new Stat("Luck", 1);

    Stat statHp = new Stat("HP", 1);
    Stat statMp = new Stat("MP", 1);

    Stat statBaseAttack = new Stat("Base Attack", 0);
    Stat statBaseDefense = new Stat("Base Defense", 0);
    Stat statAttack = new Stat("Attack", 0);
    Stat statDefense = new Stat("Defense", 0);

    private Stat statDodge = new Stat("Dodge Chance", 0);
    private Stat statCritical = new Stat("Critical Hit Chance", 0);

    Weapon equippedWeapon = new Weapon("fists", 0, 1);
    Armor equippedArmor = new Armor("unarmored", 0, 0);

    List<Spell> spellList = new ArrayList<Spell>();

    void rollAllStats() {
        Random r = new Random();

        statStr.max = r.nextInt(6) + 12;
        statWis.max = r.nextInt(6) + 12;
        statEnd.max = r.nextInt(6) + 12;
        statDex.max = r.nextInt(6) + 12;
        statLuc.max = r.nextInt(6) + 12;

        statStr.val = statStr.max;
        statWis.val = statWis.max;
        statEnd.val = statEnd.max;
        statDex.val = statDex.max;
        statLuc.val = statLuc.max;

        statHp.max = (int) (statEnd.val * 1.5 + r.nextInt(5));
        statMp.max = statWis.val / 2 + r.nextInt(3);

        statHp.val = statHp.max;
        statMp.val = statMp.max;

        statBaseAttack.max = statStr.val / 2 + 1;
        statBaseDefense.max = statDex.val / 4 + 1;
        statAttack.max = statBaseAttack.val;
        statDefense.max = statBaseDefense.val;

        statBaseAttack.val = statBaseAttack.max;
        statBaseDefense.val = statBaseDefense.max;
        statAttack.val = statBaseAttack.max + equippedWeapon.damageRating;
        statDefense.val = statBaseDefense.max + equippedArmor.defenseRating;

        statDodge.max = (int) Math.pow(statDex.val, (float) statDex.val / 40);
        statCritical.max = (int) Math.pow(statLuc.val, (float) statLuc.val / 40);

        statDodge.val = statDodge.max;
        statCritical.val = statCritical.max;
    }

    void levelUp() {
        Random rand = new Random();
        level++;

        statStr.addVal();
        statWis.addVal();
        statEnd.addVal();
        statDex.addVal();
        statLuc.addVal();

        statHp.max = statEnd.val * 2 + rand.nextInt(5);
        statMp.max = (int) (statWis.val * 1.5 + rand.nextInt(3));

        statBaseAttack.max = statStr.val / 2 + 1;
        statBaseDefense.max = statDex.val / 4 + 1;
        statAttack.max = statBaseAttack.val;
        statDefense.max = statBaseDefense.val;

        statBaseAttack.val = statBaseAttack.max;
        statBaseDefense.val = statBaseDefense.max;
        statAttack.val = statBaseAttack.max + equippedWeapon.damageRating;
        statDefense.val = statBaseDefense.max + equippedArmor.defenseRating;
    }

    public void healHp(int hp) {
        statHp.val += hp;
        System.out.println("\n> Healed HP by " + hp + " points.");
    }

    public void healMp(int mp) {
        statMp.val += mp;
        System.out.println("\n> Healed MP by " + mp + " points.");
    }

    void healFull() {
        statHp.val = statHp.max;
        statMp.val = statMp.max;
        System.out.println("\n> Fully restored HP and MP.");
    }

    void attack(Character target) {
        Random r = new Random();
        int a = statAttack.val + r.nextInt(3);
        target.statHp.val -= a;
        System.out.println("\n> " + this.name + " dealt " + a + " damage to " + target.name + ".");
    }

    void castDamageSpell(DamageSpell spell, Character target) {
        if (this.canCast(spell)) {
            target.statHp.val -= spell.damage;
            System.out.println("\n> " + this.name + " dealt " + spell.damage + " spell damage to " + target.name + ".");
        }
    }

    void castHealingSpell(HealingSpell spell, Character target) {
        if (this.canCast(spell)) {
            target.statHp.val += spell.healing;
            System.out.println("\n> Healed " + target.name + " by " + spell.healing + " points.");
        }
    }

    void printStats() {
        System.out.printf("\n%s's Stats:\n--------------------\nLevel: %d\nHP: %d\nMP: %d\nStrength: %d\nWisdom:" +
                        " %d\nEndurance: %d\nDexterity: %d\nLuck: %d\nAttack: %d\nDefense: %d\n--------------------",
                name, level, statHp.val, statMp.val, statStr.val, statWis.val,
                statEnd.val, statDex.val, statLuc.val, statAttack.val, statDefense.val);
    }

    private boolean canCast(Spell spell) {
        return statMp.val >= spell.mpCost;
    }

    boolean isDead() {
        return statHp.val <= 0;
    }
}