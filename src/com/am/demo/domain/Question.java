package com.am.demo.domain;

import java.util.List;

public class Question {

    private static int questionId;
    private int id= questionId++;
    private String questionDescription;
    private List<Answer> possibleAnswers;
    private String answer;

    public Question() {
    }

    public Question(String questionDescription, List<Answer> possibleAnswers, String answer) {
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

    public List<Answer> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<Answer> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
