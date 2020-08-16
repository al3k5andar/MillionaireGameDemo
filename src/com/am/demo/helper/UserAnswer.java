package com.am.demo.helper;

public class UserAnswer
{
    private String answerString;
    private boolean flag= false;
    private volatile boolean answerFlag;

    public synchronized void writeAnswer(int answer){
        while (flag){
            try{
                wait();
            }
            catch (InterruptedException e){}
        }
        this.answerString = String.valueOf(answer);
        answerFlag= true;
        flag= true;
        notifyAll();
    }

    public synchronized String readAnswer(){
        while (!flag){
            try {
                wait();
            }
            catch (InterruptedException e){}
        }
        answerFlag= false;
        flag= false;
        notifyAll();
        return answerString;
    }

    public boolean isAnswerFlag() {
        return answerFlag;
    }
}
