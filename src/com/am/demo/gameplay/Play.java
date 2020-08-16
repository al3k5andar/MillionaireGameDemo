package com.am.demo.gameplay;

import com.am.demo.actions.Action;
import com.am.demo.actions.NotificationEnum;
import com.am.demo.actions.QuizLeaderMessages;
import com.am.demo.domain.Answer;
import com.am.demo.domain.Question;
import com.am.demo.entity.Player;
import com.am.demo.helper.ConsoleInput;
import com.am.demo.helper.MyTimer;
import com.am.demo.helper.UserAnswer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Play implements Playable
{
    String[] moneyOrNext = {"money","next"};
    String[] startOrQuit= {"yes", "no"};

    @Override
    public void play(Map<Integer,Question> questionMap, Scanner scanner, Player player) {
        Map<Integer,Question> questions= new HashMap<>(Action.getSelectedQuestions(questionMap));

        int mapCapacity= questionMap.size();
        Random random= new Random();
        int questionCounter= 0;
        boolean isGameOver= false;
        while(!isGameOver){
            while(true){
                if (questions.isEmpty()){
                    System.out.println("Congratulate "+ player.getName()+", you are our new Winner");
                    break;
                }
                int randomQuestionNo= random.nextInt(mapCapacity);

                if (questions.containsKey(randomQuestionNo)){
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
                        questions.remove(randomQuestionNo);
                        questionCounter++;
                        player.setScore(PriceClass.getQuestionMoney(questionCounter));
                        QuizLeaderMessages.correctAnswer(random.nextInt(4));
                        System.out.println(player.getName() +" you won "+ player.getScore()+ " $");
                        System.out.println("\nDo you want to continue or want your money?");

                        String userInput= Action.userInputChecker(scanner, moneyOrNext,NotificationEnum.MONEY_CONTINUE);
                        if(!continuePlaying(userInput, player, questionCounter)){
                            System.out.println("Thank you "+ player.getName()+ " for playing!!!");
                            break;
                        }
                    }
                    else
                    {
                        QuizLeaderMessages.wrongAnswer(random.nextInt(4));
                        System.out.println("\nGame Over!\n");
                        break;
                    }
                }

            }
            System.out.println("\nDo you want to play again?");
            String userInput= Action.userInputChecker(scanner, startOrQuit, NotificationEnum.START_QUIT);
            if (userInput.equalsIgnoreCase("no"))
                isGameOver= true;
            else {
                questionCounter= 0;
                questions= new HashMap<>(questionMap);
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
            System.out.println( player.getName()+" you won: "+ player.getScore()+ " $");
            return false;
        }
    }
}
