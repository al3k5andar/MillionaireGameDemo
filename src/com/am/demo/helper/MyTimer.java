package com.am.demo.helper;

import java.util.concurrent.Callable;

public class MyTimer implements Callable<String>
{
    private UserAnswer userAnswer;

    public MyTimer(UserAnswer userAnswer) {
        this.userAnswer = userAnswer;
    }

    @Override
    public String call() {
        int counter= 0;

        while(counter < 30){
            counter++;

            if(userAnswer.isAnswerFlag()){
                return userAnswer.readAnswer();
            }

            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException e){}
        }
        return null;
    }

}
