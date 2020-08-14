package com.am.demo.helper;

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
        String userInput= scanner.nextLine();
        userAnswer.writeAnswer(userInput);
    }

}
