package com.am.demo.domain;

public class Answer
{
    private int id;
    private String possibleQuestionAnswer;

    public Answer() {
    }

    public Answer(int id, String possibleQuestionAnswer) {
        this.id= id;
        this.possibleQuestionAnswer = possibleQuestionAnswer;
    }

    public String getPossibleQuestionAnswer() {
        return possibleQuestionAnswer;
    }

    public void setPossibleQuestionAnswer(String possibleQuestionAnswer) {
        this.possibleQuestionAnswer = possibleQuestionAnswer;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "questionAnswer='" + possibleQuestionAnswer + '\'' +
                '}';
    }
}
