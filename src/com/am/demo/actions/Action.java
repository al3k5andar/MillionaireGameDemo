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
                List<String> answersList= new ArrayList<>();
                String[] questionSegments= questionData.split("->");
                String description= questionSegments[1];
                String answerDescription= questionSegments[2];
                question.setQuestionDescription(description);
                question.setAnswer(new Answer(answerDescription));

                String answerData;
                while((answerData= answerReader.readLine())!= null){
                    String[] answerSegments= answerData.split("->");
                    if(answerSegments[0].equals(questionSegments[0])) {
                        answersList.addAll(Arrays.asList(answerSegments).subList(1, answerSegments.length));
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
}
