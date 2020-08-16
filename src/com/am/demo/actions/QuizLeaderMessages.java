package com.am.demo.actions;

public class QuizLeaderMessages
{
    public static void correctAnswer(int randomInt){

        switch (randomInt){
            case 0:
                System.out.println("\nBravo... You are the best!!!");
                break;
            case 1:
                System.out.println("\nHmm I think that is a correct answer!!!");
                break;
            case 2:
                System.out.println("\nMaybe you are our next Millionaire, the answer is correct!");
                break;
            case 3:
                String[] answerArray= {"Answer","is","correct"};
                System.out.println();
                for (String s: answerArray){
                    try{
                        System.out.print(s+ " ");
                        Thread.sleep(700);
                    }
                    catch (InterruptedException e){}
                }
                System.out.println();
        }
    }
    public static void wrongAnswer(int randomInt){

        switch (randomInt){
            case 0:
                System.out.println("\nOh nooo, that is a wrong answer!");
                break;
            case 1:
                System.out.println("\nMaybe you will have more luck next time :)");
                break;
            case 2:
                System.out.println("\nI think you are lost all your money.");
                break;
            case 3:
                System.out.println("\nNo, you are wrong");
                break;
        }
    }
}
