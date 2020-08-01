package com.am.demo.domain;

public class Answer
{
    private String questionAnswer;

    public Answer() {
    }

    public Answer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "questionAnswer='" + questionAnswer + '\'' +
                '}';
    }
}
