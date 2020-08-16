package com.am.demo;

import com.am.demo.actions.Action;
import com.am.demo.domain.Question;
import com.am.demo.entity.Player;
import com.am.demo.gameplay.Play;
import com.am.demo.gameplay.Playable;

import java.util.Map;
import java.util.Scanner;

public class Main {

    public static Scanner scanner= new Scanner(System.in);
    public static void main(String[] args) {

        Map<Integer, Question> questionMap= Action.readFromDisc();

        boolean exit= false;

        System.out.println("Welcome to Millionaire.....");
        menu();

        while (!exit) {
            int userChoose = scanner.nextInt();

            switch (userChoose) {

                case 1:
                    System.out.println("Enter your name: ");
                    scanner.nextLine();
                    String userName = scanner.nextLine();
                    Player player = new Player(userName);
                    Playable start= new Play();
                    start.play(questionMap, scanner, player);
                    System.out.println();
                    menu();
                    break;
                case 2:
                    menu();
                    break;
                case 3:
                    System.out.println("Game Quit");
                    exit= true;
                    break;
                default:
                    System.out.println("For Menu pres 3!");
            }
        }
    }
    private static void menu()
    {
        System.out.println();
        System.out.println("1. Play Game");
        System.out.println("2. Menu");
        System.out.println("3. Quit");
        System.out.println("\nChoose Action: ");
    }
}
