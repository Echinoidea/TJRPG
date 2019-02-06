package com.hookgabr;

public class Game {

    public static void slowPrint(String text) {
        for (int i = 1; i < text.length(); i++) {
            System.out.printf("%c", text.charAt(i));
            try {
                Thread.sleep(25);
            } catch (InterruptedException iE) {
                System.out.println("!!! ERROR INTERRUPTED EXCEPTION");
            }
        }
    }
}
