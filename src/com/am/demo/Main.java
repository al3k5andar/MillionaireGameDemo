package com.am.demo;

import com.am.demo.actions.Action;
import com.am.demo.domain.Question;

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

        Action.playGame(questionMap, scanner);
    }
}
