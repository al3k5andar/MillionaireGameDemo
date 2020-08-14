package com.am.demo.gameplay;

public class PriceClass
{
    public static int getQuestionMoney(int questionNo){

        int moneyWon= 0;

        switch (questionNo){
            case 1: moneyWon= 100;
                break;
            case 2: moneyWon= 200;
                break;
            case 3: moneyWon= 300;
                break;
            case 4: moneyWon= 500;
                break;
            case 5: moneyWon= 1000;
                break;
            case 6: moneyWon= 2000;
                break;
            case 7: moneyWon= 4000;
                break;
            case 8: moneyWon= 8000;
                break;
            case 9: moneyWon= 16000;
                break;
            case 10: moneyWon= 32000;
                break;
            case 11: moneyWon= 64000;
                break;
            case 12: moneyWon= 125000;
                break;
            case 13: moneyWon= 250000;
                break;
            case 14: moneyWon= 500000;
                break;
            case 15: moneyWon= 1000000;
                break;
        }
        return moneyWon;

    }
}
