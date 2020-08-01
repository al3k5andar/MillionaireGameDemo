package com.am.demo.domain;

import java.util.List;

public class Question {

    private static int questionId;
    private int id= questionId++;
    private String questionDescription;
    private List<String> possibleAnswers;
    private Answer answer;

    public Question() {
    }

    public Question(String questionDescription, List<String> possibleAnswers, Answer answer) {
        this.questionDescription = questionDescription;
        this.possibleAnswers = possibleAnswers;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionDescription='" + questionDescription + '\'' +
                ", possibleAnswers=" + possibleAnswers +
                '}';
    }
}
