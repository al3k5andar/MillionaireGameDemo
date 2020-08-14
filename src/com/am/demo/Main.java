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

        Playable playable= new Play();
        playable.play(questionMap,scanner, new Player("Mike"));

//        UserAnswer userAnswer= new UserAnswer();
//        ConsoleInput consoleInput= new ConsoleInput(scanner, userAnswer);
//        MyTimer myTimer= new MyTimer(userAnswer);
//
//        Thread input= new Thread(consoleInput);
//        Thread timer= new Thread(myTimer);
//
//        input.start();
//        timer.start();
//
//        try {
//            input.join();
//            timer.join();
//        }
//        catch (InterruptedException e){}


//        boolean exit= false;
//
//        System.out.println("Welcome to Millionaire.....");
//        menu();
//
//        while (!exit) {
//            int userChoose = scanner.nextInt();
//
//            switch (userChoose) {
//
//                case 1:
//                    System.out.println("Enter your name: ");
//                    scanner.nextLine();
//                    String userName = scanner.nextLine();
//                    Player player = new Player(userName);
//                    boolean isGameFinished= Action.playGame(questionMap, scanner, player);
//                    while(isGameFinished)
//                    {
//                        System.out.println("\n\nDo you want to play again ?");
//                        System.out.println("For new game hit enter, for quit type 'quit'.\n");
//                        String confirmation= scanner.nextLine();
//                        if(confirmation.equalsIgnoreCase("quit")){
//                            isGameFinished= false;
//                            menu();
//                        }
//                        else {
//                            Action.playGame(questionMap, scanner, player);
//                        }
//                    }
//                    break;
//                case 2:
//                    menu();
//                    break;
//                case 3:
//                    System.out.println("Game Quit");
//                    exit= true;
//                    break;
//                default:
//                    System.out.println("For Menu pres 3!");
//            }
//        }
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
