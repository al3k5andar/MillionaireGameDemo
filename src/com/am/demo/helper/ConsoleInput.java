package com.am.demo.helper;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInput implements Runnable
{
    private Scanner scanner;
    private UserAnswer userAnswer;

    public ConsoleInput(Scanner scanner, UserAnswer userAnswer) {
        this.scanner = scanner;
        this.userAnswer= userAnswer;
    }

    @Override
    public void run() {
        boolean isCorrect= false;

        while (!isCorrect){
            int userInput;
            try{
                userInput= scanner.nextInt();
                if(userInput < 1 || userInput > 4)
                    throw new InputMismatchException();
                isCorrect= true;
                userAnswer.writeAnswer(userInput);
                scanner.nextLine();
            }
            catch (InputMismatchException e){
                System.out.println("You can insert only numbers from 1 to 4");
                scanner.nextLine();
            }
        }

    }

}
