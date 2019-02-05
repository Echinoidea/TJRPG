package com.hookgabr;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Character {

    public String name = null;

    public boolean canFight = false;

    public int level = 1;

    public Stat statStr = new Stat("Strength", 1);
    public Stat statWis = new Stat("Wisdom", 1);
    public Stat statEnd = new Stat("Endurance", 1);
    public Stat statDex = new Stat("Dexterity", 1);
    public Stat statLuc = new Stat("Luck", 1);

    public Stat statHp = new Stat("HP", 1);
    public Stat statMp = new Stat("MP", 1);

    public Stat statBaseAttack = new Stat("Base Attack", 0);
    public Stat statBaseDefense = new Stat("Base Defense", 0);
    public Stat statAttack = new Stat("Attack", 0);
    public Stat statDefense = new Stat("Defense", 0);

    public Stat statDodge = new Stat("Dodge Chance", 0);
    public Stat statCrit = new Stat("Critical Hit Chance", 0);

    public Weapon equippedWeapon = new Weapon("fists", 0, 1);
    public Armor equippedArmor = new Armor("unarmored", 0, 0);

    public List<Spell> spellList = new ArrayList<Spell>();

    public void rollStats() {
        Random rand = new Random();
        statStr.max = (int)Math.pow(rand.nextInt(6), (1 + ((float)level / 10))) + level;
        statWis.max = (int)Math.pow(rand.nextInt(6), (1 + ((float)level / 10))) + level;
        statEnd.max = (int)Math.pow(rand.nextInt(6), (1 + ((float)level / 10))) + level;
        statDex.max = (int)Math.pow(rand.nextInt(6), (1 + ((float)level / 10))) + level;
        statLuc.max = (int)Math.pow(rand.nextInt(6), (1 + ((float)level / 10))) + level;

        statStr.val = statStr.max;
        statWis.val = statWis.max;
        statEnd.val = statEnd.max;
        statDex.val = statDex.max;
        statLuc.val = statLuc.max;

        statHp.max = statEnd.val * 2 + rand.nextInt(5);
        statMp.max = (int)(statWis.val * 1.5 + rand.nextInt(3));

        statHp.val = statHp.max;
        statHp.val = statHp.max;

        statBaseAttack.max = statStr.val / 2 + 1;
        statBaseDefense.max = statDex.val / 4 + 1;
        statAttack.max = statBaseAttack.val;
        statDefense.max = statBaseDefense.val;

        statBaseAttack.val = statBaseAttack.max;
        statBaseDefense.val = statBaseDefense.max;
        statAttack.val = statBaseAttack.max;
        statAttack.val = statBaseAttack.max;

        statDodge.val = (int)Math.pow(statDex.val, (float)statDex.val / 10);
        statCrit.val = (int)Math.pow(statLuc.val, (float)statLuc.val / 10);

        statDodge.val = statDodge.max;
        statCrit.val = statCrit.max;
    }

    public void levelUp() {
        Random rand = new Random();
        level++;

        statStr.addVal();
        statWis.addVal();
        statEnd.addVal();
        statDex.addVal();
        statLuc.addVal();

        statHp.max = statEnd.val * 2 + rand.nextInt(5);
        statMp.max = (int)(statWis.val * 1.5 + rand.nextInt(3));

        statBaseAttack.max = statStr.val / 2;
        statBaseAttack.max = statDex.val / 5;
        statAttack.max = statBaseAttack.val;
        statDefense.max = statBaseDefense.val;
    }

    public void healHp(int hp) {
        statHp.val += hp;
        System.out.println("\n> Healed HP by " + hp + " points.");
    }

    public void healMp(int mp) {
        statMp.val += mp;
        System.out.println("\n> Healed MP by " + mp + " points.");
    }

    public void healFull() {
        statHp.val = statHp.max;
        statMp.val = statMp.max;
        System.out.println("\n> Fully restored HP and MP.");
    }

    public void attack(Character target) {
        target.statHp.val -= statAttack.val;
        System.out.println("\n> " + this.name + " dealt " + statAttack.val + " damage to " + target.name + ".");
    }

    public void castDamageSpell(DamageSpell spell, Character target) {
        if (this.canCast(spell)) {
            target.statHp.val -= spell.damage;
            System.out.println("\n> " + this.name + " dealt " + spell.damage + " spell damage to " + target.name + ".");
        }
    }

    public void castHealingSpell(HealingSpell spell, Character target) {
        if (this.canCast(spell)) {
            target.statHp.val += spell.healing;
            System.out.println("\n> Healed " + target.name + " by " + spell.healing + " points.");
        }
    }

    public void printStats() {
        /*String stats = String.format("\n%s's Stats:\n--------------------\nLevel: %d\nHP: %d\nMP: %d\nStrength: %d\nWisdom:" +
                        " %d\nEndurance: %d\nDexterity: %d\nLuck: %d\nAttack: %d\nDefense: %d\n--------------------",
                name, level, statHp.val, statMp.val, statStr.val, statWis.val,
                statEnd.val, statDex.val, statLuc.val, statAttack.val, statDefense.val);
        System.out.println(stats);*/
        System.out.printf("\n%s's Stats:\n--------------------\nLevel: %d\nHP: %d\nMP: %d\nStrength: %d\nWisdom:" +
                        " %d\nEndurance: %d\nDexterity: %d\nLuck: %d\nAttack: %d\nDefense: %d\n--------------------\n",
                name, level, statHp.val, statMp.val, statStr.val, statWis.val,
                statEnd.val, statDex.val, statLuc.val, statAttack.val, statDefense.val);
    }

    public boolean canCast(Spell spell) {
        return statMp.val >= spell.mpCost;
    }

    public boolean isDead() {
        return statHp.val <= 0;
    }
}