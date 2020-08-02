package com.am.demo.actions;

import com.am.demo.domain.Answer;
import com.am.demo.domain.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Action
{
    public static Map<Integer, Question> readFromDisc(){
        Map<Integer, Question> questionMap= new HashMap<>();

        try(BufferedReader questionReader= new BufferedReader(new FileReader("questions.txt"));
            BufferedReader answerReader= new BufferedReader(new FileReader("answers.txt")))
        {
            String questionData;
            while((questionData= questionReader.readLine())!= null)
            {
                Question question= new Question();
                List<Answer> answersList= new ArrayList<>();
                String[] questionSegments= questionData.split("->");
                String description= questionSegments[1];
                String answerDescription= questionSegments[2];
                question.setQuestionDescription(description);
                question.setAnswer(answerDescription);

                String answerData;
                while((answerData= answerReader.readLine())!= null){
                    String[] answerSegments= answerData.split("->");
                    if(answerSegments[0].equals(questionSegments[0])) {
                        for (int i = 1; i < answerSegments.length; i++) {
                            answersList.add(new Answer(i,answerSegments[i]));
                        }
                        break;
                    }
                }
                question.setPossibleAnswers(answersList);
                questionMap.put(question.getId(),question);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return questionMap;
    }

    public static void playGame(Map<Integer,Question> questionMap, Scanner scanner){
        Random random= new Random();
        int mapCapacity= questionMap.size();
        int answerNumber;
        boolean continueFlag= false;

        while(!continueFlag){
            int randomQuestion= random.nextInt(mapCapacity);
            if(!questionMap.containsKey(randomQuestion)) {
                continue;
            }
            Question question= questionMap.get(randomQuestion);
            System.out.println("Question: \n"+ question.getQuestionDescription() + "\n");
            for (int i = 0; i < question.getPossibleAnswers().size(); i++) {
                System.out.println((i+1) + " " + question.getPossibleAnswers().get(i).getPossibleQuestionAnswer());
            }
            System.out.println("\nEnter answer number hire and hit Enter: ");
            answerNumber= scanner.nextInt();
            if(!isCorrect(answerNumber,question)){
                continueFlag= true;
                System.out.println("Game Over");
            }
            else
            {
                questionMap.remove(question.getId());
                if(questionMap.isEmpty()) {
                    continueFlag = true;
                    System.out.println("!!! Congratulate you are a new Millionaire !!!");
                }
            }

        }
    }
    private static boolean isCorrect(int answerNumber, Question question){
        List<Answer> answers= question.getPossibleAnswers();
        String userAnswer= null;
        for (Answer answer: answers){
            if(answer.getId()== answerNumber)
                userAnswer= answer.getPossibleQuestionAnswer();
        }

        return (userAnswer != null) && userAnswer.equals(question.getAnswer());
    }
}
