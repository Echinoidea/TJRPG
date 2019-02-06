package com.hookgabr;

import java.util.*;

public class Battle {

    private Player player;
    private Monster monster;

    Battle(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;

        Game.slowPrint("\n> Battle started between " + player.name + " and " + monster.name + "!");

        startBattleLoop();
    }

    private boolean isRunning = true;

    private void startBattleLoop() {
        boolean isPlayerTurn = true;
        Scanner scan = new Scanner(System.in);
        //Random r = new Random();

        isRunning = !player.isDead() && !monster.isDead();
        isPlayerTurn = player.statDex.val > monster.statDex.val;

        while (isRunning) {
            isRunning = !player.isDead() && !monster.isDead();

            if (isPlayerTurn) {
                displayBattleMenu();
                System.out.print("> ");

                String input = scan.nextLine();
                switch (input.toUpperCase()) {
                    case "1":
                        player.attack(monster);
                        if (monster.isDead()) {
                            defeatMonster();
                        }
                        break;
                    case "MELEE":
                        player.attack(monster);
                        if (monster.isDead()) {
                            defeatMonster();
                        }
                        break;
                    case "2":
                        player.printSpellList();
                        break;
                    case "SPELLS":
                        player.printSpellList();
                        break;
                    case "3":
                        player.printItemList();
                        break;
                    case "ITEMS":
                        player.printItemList();
                        break;
                    case "4":
                        if (player.statDex.max > monster.statDex.max) {
                            isRunning = false;
                            break;
                        }
                        break;
                    case "FLEE":
                        if (player.statDex.max > monster.statDex.max) {
                            isRunning = false;
                            break;
                        }
                        break;
                    default:
                        System.out.println("\n> INVALID INPUT");
                        continue;
                }

                isPlayerTurn = false;
            }
            else {
                if (!monster.isDead()) {
                    System.out.println("\n> " + monster.name + "'s turn.");
                    monsterAttack();
                    System.out.println("\n> Press Enter");
                    scan.nextLine();
                    isPlayerTurn = true;
                } else {
                    break;
                }
            }
        }
        scan.close();
        isRunning = false;
        System.out.println("\n// BATTLE OVER");
    }

    private void displayBattleMenu() {
        String battleMenu = "\n/-/-/-/-/-/-/-/-/-/\nHP: " + player.statHp.val + "\nMP: "
                + player.statMp.val + "\n--------------------\n1: MELEE\n2: SPELLS\n" +
                "3: ITEMS\n4: FLEE\n/-/-/-/-/-/-/-/-/-/";
        System.out.println(battleMenu);
    }

    private HealingSpell checkForHealingSpell(List<Spell> spellList) {
        boolean containsHealingSpell = false;
        HealingSpell hSpell = null;
        if (!spellList.isEmpty()) {
            for (Spell s : spellList) {
                if (s instanceof HealingSpell) {
                    containsHealingSpell = true;
                    hSpell = (HealingSpell) s;
                }
            }
        }
        else {
            containsHealingSpell = false;
        }
        return hSpell;
    }

    private DamageSpell checkForDamageSpell(List<Spell> spellList) {
        boolean containsDamageSpell = false;
        DamageSpell dSpell = null;
        if (!spellList.isEmpty()) {
            for (Spell s : spellList) {
                if (s instanceof DamageSpell) {
                    containsDamageSpell = true;
                    dSpell = (DamageSpell) s;
                }
            }
        }
        else {
            containsDamageSpell = false;
        }
        return dSpell;
    }

    private void monsterAttack() {
        isRunning = !player.isDead() && !monster.isDead();
        Random r = new Random();
        int rNum = r.nextInt(100);
        if (!monster.isDead()) {
            if (rNum >= 50) {
                if (monster.statHp.val < monster.statHp.val / 2 && monster.spellList.contains(checkForHealingSpell(monster.spellList))) {
                    HealingSpell hSpell;
                    System.out.println("\n> " + monster.name + " casts " + checkForHealingSpell(monster.spellList).name + "!");
                    monster.castHealingSpell(checkForHealingSpell(monster.spellList), monster);
                }
                else if (monster.spellList.contains(checkForDamageSpell(monster.spellList))) {
                    HealingSpell dSpell;
                    System.out.println("\n> " + monster.name + " casts " + checkForDamageSpell(monster.spellList).name + "!");
                    monster.castDamageSpell(checkForDamageSpell(monster.spellList), monster);
                }
            }

            if (player.level > monster.level + 5 && rNum >= 40) {
                System.out.println("\n> " + monster.name + "fled!");
                isRunning = false;
            } else {
                monster.attack(player);
                isRunning = !player.isDead() && !monster.isDead();
            }
        }

        if (player.isDead()) {
            loseBattle();
        }
    }

    private void defeatMonster() {
        System.out.println("\n> You defeated " + monster.name + "!");
        player.exp += monster.expYield;
        player.gold += monster.goldYeild;
        if (player.canLevelUp()) {
            player.levelUp();
            System.out.println("\n> You leveled up!");
            player.printStats();
        }
        System.out.printf("\n> You earned %d EXP and found %d gold!", monster.expYield, monster.goldYeild);

        /*Random r = new Random();
        int rNum = r.nextInt(100);
        if (rNum >= monster.lootChance) {
            // TODO: Give leveled item?
        }
        */
        // Return to the Arena
    }

    private void loseBattle() {
        System.out.println("\n> You were defeated and lost half of your gold.");
        player.gold -= player.gold / 2;
        // Return to the Arena
    }
}
// TODO: Game over stuff
// TODO: Continue to "arena" after battle