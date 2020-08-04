package com.am.demo.actions;

import com.am.demo.domain.Answer;
import com.am.demo.domain.Question;
import com.am.demo.entity.Player;

import java.io.*;
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

    public static void playGame(Map<Integer,Question> questionMap, Scanner scanner, Player player){
        Random random= new Random();
        int mapCapacity= questionMap.size();
        int answerNumber;
        boolean continueFlag= false;
        int playerScore= 100;

        while(!continueFlag){
            int randomQuestion= random.nextInt(mapCapacity);
            if(questionMap.isEmpty()) {
                continueFlag = true;
                savePlayerScore(player);
                System.out.println("!!! Congratulate "+ player.getName() +" you are a new Millionaire !!!");
            }
            else
            {
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
                    player.setScore(0);
                    continueFlag= true;
                    System.out.println("Game Over");
                    savePlayerScore(player);
                }
                else
                {
                    player.setScore(playerScore);
                    playerScore *= 2;
                    questionMap.remove(question.getId());
                    System.out.println(player.getName() + " you won "+ player.getScore() + " points");
                    System.out.println("If you want to continue pres 'Y' or 'Q' if you want your cash and quit the game.");
                    scanner.nextLine();
                    String playerChoose= scanner.nextLine();
                    if(playerChoose.equalsIgnoreCase("q")) {
                        System.out.println("Thank you for playing... You won: "+ player.getScore());
                        continueFlag= true;
                        savePlayerScore(player);
                    }
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

    private static void savePlayerScore(Player player){
        try(BufferedWriter writer= new BufferedWriter(new FileWriter("stats.txt", true))){
            StringBuilder builder= new StringBuilder();
            builder.append(player.getName());
            builder.append(",");
            builder.append(player.getScore());
            builder.append("\n");
            writer.write(builder.toString());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void showAllPlayersScore(List<Player> playerList){
        Comparator<Player> comparator= (p1, p2) -> p1.getScore() - p2.getScore();

        playerList.sort(comparator);
        for(Player player: playerList){
            System.out.println("Player: "+ player.getName()+ ".........."+ player.getScore());
        }
        System.out.println();
    }

    public static List<Player> getPlayersScore(){
        List<Player> playerList= new ArrayList<>();
        try(BufferedReader reader= new BufferedReader(new FileReader("stats.txt"))){
            String data;
            while((data= reader.readLine())!= null){
                String[] dataArray= data.split(",");
                playerList.add(new Player(dataArray[0], Integer.parseInt(dataArray[1])));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return playerList;
    }
}
