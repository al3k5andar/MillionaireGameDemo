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

    public static String userInputChecker(Scanner scanner, String[] possibleUserInputs, NotificationEnum notificationEnum){
        String userInput;
        List<String> possibleUserInputList= new ArrayList<>(Arrays.asList(possibleUserInputs));
        do {
            System.out.println(notificationManager(notificationEnum));
            userInput = scanner.nextLine();
        } while (!possibleUserInputList.contains(userInput.toLowerCase()));

        return userInput;
    }

    public static String notificationManager(NotificationEnum notificationEnum){
        switch (notificationEnum){
            case START_QUIT:
                return "\nFor New Game type 'Yes', for 'Quit' type 'No'\n";
            case MONEY_CONTINUE:
                return "\nFor continue type 'Next', if you want your money type 'Money'\n";
        }
        return null;
    }
    public static Map<Integer, Question> getSelectedQuestions(Map<Integer, Question> mainMap){
        Map<Integer, Question> autoSelectedQuestions= new HashMap<>();
        Random random= new Random();
        int mapSize= mainMap.size();
        int counter= 0;

        while(counter< 15)
        {
            int randomInt= random.nextInt(mapSize);
            if(!autoSelectedQuestions.containsKey(randomInt)) {
                autoSelectedQuestions.put(counter++, mainMap.get(randomInt));
                counter++;
            }
        }
        return autoSelectedQuestions;
    }

}
