package com.am.demo;

import com.am.demo.actions.Action;
import com.am.demo.domain.Question;
import com.am.demo.entity.Player;

import java.util.Map;
import java.util.Scanner;

public class Main {

    public static Scanner scanner= new Scanner(System.in);
    public static void main(String[] args) {

        Map<Integer, Question> questionMap= Action.readFromDisc();

//        for (Map.Entry<Integer,Question> entry: questionMap.entrySet()){
//            System.out.println("ID: " + entry.getKey());
//            System.out.println("Question: " + entry.getValue().getQuestionDescription());
//            System.out.println("Answers:");
//            for(Answer answer: entry.getValue().getPossibleAnswers())
//                System.out.println(answer.getPossibleQuestionAnswer());
//        }

//        Action.playGame(questionMap, scanner);

        System.out.println("Welcome to Millionaire.....");

        System.out.println("1. Play Game");
        System.out.println("2. Quit");
        System.out.println("\nChoose Action: ");

        int userChoose= scanner.nextInt();

        switch (userChoose){
            case 1:
                System.out.println("Enter your name: ");
                scanner.nextLine();
                String userName= scanner.nextLine();
                Player player= new Player(userName);
                Action.playGame(questionMap,scanner,player);
                break;
            case 2:
                System.out.println("Game Quit");
                break;
            default:
                System.exit(0);
        }
    }
}
