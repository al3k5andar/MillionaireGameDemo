package com.am.demo.gameplay;

import com.am.demo.domain.Answer;
import com.am.demo.domain.Question;
import com.am.demo.entity.Player;
import com.am.demo.helper.ConsoleInput;
import com.am.demo.helper.MyTimer;
import com.am.demo.helper.UserAnswer;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Play implements Playable
{
    private Map<Integer, Question> questionMap;
    private Scanner scanner;
    private Player player;

    @Override
    public void play(Map<Integer,Question> questionMap, Scanner scanner, Player player) {
        this.questionMap= questionMap;
        this.scanner= scanner;
        this.player= player;

        int mapCapacity= questionMap.size();
        Random random= new Random();
        int questionCounter= 0;

        while(true){
            if (questionMap.isEmpty()){
                System.out.println("Congratulate "+ player.getName()+" you are our new Winner");
                break;
            }
            int randomQuestionNo= random.nextInt(mapCapacity);

            if (questionMap.containsKey(randomQuestionNo)){
                Question question= questionMap.get(randomQuestionNo);
                System.out.println("Question: "+ question.getQuestionDescription()+ "\n");
                for(Answer answer: question.getPossibleAnswers())
                    System.out.println(answer.getId()+". "+ answer.getPossibleQuestionAnswer());

                System.out.println("Enter answer number and hit enter: ");
                UserAnswer userAnswer= new UserAnswer();
                ConsoleInput consoleInput= new ConsoleInput(scanner, userAnswer);

                ExecutorService executorService= Executors.newFixedThreadPool(2);
                executorService.execute(consoleInput);
                Future<String> timerThread= executorService.submit(new MyTimer(userAnswer));

                String answer= null;
                try{
                    answer= timerThread.get();
                    executorService.shutdown();
                }
                catch (InterruptedException | ExecutionException e){}


                if(isCorrect(answer,question)){
                    questionMap.remove(randomQuestionNo);
                    questionCounter++;
                    player.setScore(PriceClass.getQuestionMoney(questionCounter));
                    System.out.println("\nDo you want to continue or want your money?");

                    String userInput= userInputChecker(scanner);
                    if(!continuePlaying(userInput, player, questionCounter)){
                        System.out.println("Thank you for playing!!!");
                        break;
                    }
                }
                else
                {
                    System.out.println("Game Over!\n");
                    System.out.println("Pres any key for exit.");
                    break;
                }
            }
        }
    }

    private boolean isCorrect(String answerNo, Question question){
        if(answerNo == null) {
            return false;
        }
        Answer userAnswer= null;
        for(Answer answer: question.getPossibleAnswers())
            if(answer.getId()== Integer.parseInt(answerNo))
                userAnswer= answer;
        return  (userAnswer!= null) && (question.getAnswer().equals(userAnswer.getPossibleQuestionAnswer()));
    }

    private boolean continuePlaying(String userInput, Player player, int questionCounter){

        if(userInput.equalsIgnoreCase("next"))
            return true;
        else
        {
            player.setScore(PriceClass.getQuestionMoney(questionCounter));
            System.out.println("You won: "+ player.getScore()+ " $");
            return false;
        }
    }
    private String userInputChecker(Scanner scanner){
        String userInput;
        String[] possibleUserInputs= {"money","next"};
        List<String> possibleUserInputList= new ArrayList<>(Arrays.asList(possibleUserInputs));
        do {
            System.out.println("\nFor continue type 'Next', if you want your money type 'Money'\n");
            userInput = scanner.nextLine();
        } while (!possibleUserInputList.contains(userInput.toLowerCase()));

        return userInput;
    }
}
