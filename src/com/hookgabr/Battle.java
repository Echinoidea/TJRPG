package com.hookgabr;

import java.util.*;

public class Battle {

    public Player player;
    public Monster monster;

    private boolean isPlayerTurn = true;

    public Battle() { }

    public Battle(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;

        System.out.println("\n$$$$$$$$$$$$$$$$$$$$$\nBATTLE STARTED BETWEEN " + player.name + " AND " + monster.name +
                "\n$$$$$$$$$$$$$$$$$$$$$\n");

        startBattleLoop();
    }

    private boolean isRunning = true;

    public void startBattleLoop() {
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
                            System.out.println("\n$$$$$$$$$$$$$$$$$$$$$\nDEFEATED " + monster.name +
                                    "\n$$$$$$$$$$$$$$$$$$$$$");
                        }
                        break;
                    case "MELEE":
                        player.attack(monster);
                        if (monster.isDead()) {
                            System.out.println("\n$$$$$$$$$$$$$$$$$$$$$\nDEFEATED " + monster.name +
                                    "\n$$$$$$$$$$$$$$$$$$$$$");
                            break;
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
                        System.out.println("INVALID INPUT");
                        continue;
                }

                isPlayerTurn = false;
                isRunning = false;
            }
            else {
                System.out.println("\n$$$$$$$$$$$$$$$$$$$$$\n" + monster.name + "'s TURN\n$$$$$$$$$$$$$$$$$$$$$\n");
                monsterAttack();
                isPlayerTurn = true;
            }
            //scan.nextLine();
        }
        scan.close();
        System.out.println("\n$$$$$$$$$$$$$$$$$$$$$\nBATTLE OVER\n$$$$$$$$$$$$$$$$$$$$$\n");
    }

    private void displayBattleMenu() {
        String battleMenu = "\n####################\nHP: " + player.statHp.val + "\nMP: "
                + player.statMp.val + "\n--------------------\n1: MELEE\n2: SPELLS\n3: ITEMS\n4: FLEE" +
                "\n####################";
        System.out.println(battleMenu);
    }

    private void monsterAttack() {
        isRunning = !player.isDead() && !monster.isDead();
        Random r = new Random();
        int rNum = r.nextInt(100);
        if (!monster.isDead()) {
            if (rNum >= 60) {
                if (monster.statHp.val < monster.statHp.val / 2 && monster.spellList.contains(HealingSpell.class)) {
                    HealingSpell hSpell = null;
                    for (Spell s : monster.spellList) {
                        if (s.getClass() == HealingSpell.class) {
                            hSpell = (HealingSpell) s;
                            break;
                        }
                    }
                    if (hSpell != null && monster.canCast(hSpell)) {
                        System.out.println("\n$$$$$$$$$$$$$$$$$$$$$\n" + monster.name + "CASTED " + hSpell.name + "\n$$$$$$$$$$$$$$$$$$$$$\n");
                        monster.castHealingSpell(hSpell, monster);
                    }
                } else if (monster.spellList.contains(DamageSpell.class)) {
                    DamageSpell dSpell = null;
                    for (Spell s : monster.spellList) {
                        if (s.getClass() == DamageSpell.class) {
                            dSpell = (DamageSpell) s;
                            break;
                        }
                    }
                    if (dSpell != null && monster.canCast(dSpell)) {
                        System.out.println("\n$$$$$$$$$$$$$$$$$$$$$\n" + monster.name + "CASTED " + dSpell.name + "\n$$$$$$$$$$$$$$$$$$$$$\n");
                        monster.castDamageSpell(dSpell, monster);
                        isRunning = !player.isDead() && !monster.isDead();
                    }
                }
            }
            if (player.level > monster.level + 5 && rNum >= 40) {
                System.out.println("\n$$$$$$$$$$$$$$$$$$$$$\nTHE MONSTER FLED\n$$$$$$$$$$$$$$$$$$$$$\n");
                isRunning = false;
            } else {
                monster.attack(player);
                isRunning = !player.isDead() && !monster.isDead();
            }
        }
    }
}
// TODO: Give rewards for defeating Monster
// TODO: Game over stuff
// TODO: Continue to "arena" after battle