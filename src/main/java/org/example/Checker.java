package org.example;

import java.util.Objects;
import java.util.Scanner;

public class Checker {

    private int limit = 4;
    int FirstNumber;
    String Action;
    int secondNumber;

    public Integer InputFirstNumber(){
        this.FirstNumber = Integer.parseInt(checkString());
        return FirstNumber;
    }

    public String InputAction(){
        this.Action = checkAction();
        return Action;
    }

    public Integer InputSecondNumber(){
        this.secondNumber = Integer.parseInt(checkSecondString());
        return secondNumber;
    }

    public String checkString() {
        String strNumber;
        do {
            System.out.println("Input number");
            Scanner input = new Scanner(System.in);
            strNumber = input.nextLine();
        } while (isEmpty(strNumber) || !isNumber(strNumber) || !isCorrectLength(strNumber));
        return strNumber;
    }

    public String checkSecondString(){
        String strNumber;
        if (isDivision(Action)) {
            strNumber=checkDivisionSecondString();
        } else{
            strNumber=checkString();
        }
        return strNumber;
    }

    public String checkDivisionSecondString() {
        String strNumber;
        do {
            System.out.println("Input number");
            Scanner input = new Scanner(System.in);
            strNumber = input.nextLine();
        } while (isEmpty(strNumber) || !isNumber(strNumber) || !isCorrectLength(strNumber) || isZeroDivision(strNumber));
        return strNumber;
    }


    public String checkAction() {
        String strAction;
        do {
            System.out.println("Input action:+-/*");
            Scanner input = new Scanner(System.in);
            strAction = input.nextLine();
        } while (isEmpty(strAction) || !isOneOf(strAction));
        return strAction;
    }


    public boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            System.out.println("This is not number!!");
            return false;
        }
    }

    public boolean isEmpty(String string) {
        if (string.isEmpty()) {
            System.out.println("This is not number!!");
            return true;
        }
        return false;
    }

    public boolean isCorrectLength(String string) {
        if (string.length() > limit) {
            System.out.println("This is not correct length!!");
            return false;
        }
        return true;
    }

    public boolean isZeroDivision(String string) {
        if (Integer.parseInt(string) == 0) {
            System.out.println("Division by zero is prohibited!");
            return true;
        }
        return false;
    }

    public boolean isOneOf(String string) {
        if (Objects.equals(string, "+") || Objects.equals(string, "-") || Objects.equals(string, "*") || Objects.equals(string, "/")) {
            return true;
        } else {
            System.out.println("This is not correct symbol!");
        }
        return false;
    }

    public boolean isDivision(String string) {
        return Objects.equals(string, "/");
    }



}
