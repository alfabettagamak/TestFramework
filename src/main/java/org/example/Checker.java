package org.example;

import java.util.Scanner;

public class Checker {

    private int limit = 4;

    public void checkString(){
        String strNumber;
        do {
            System.out.println("Input number");
            Scanner input = new Scanner(System.in);
            strNumber = input.nextLine();
        } while (isEmpty(strNumber) || !isNumber(strNumber) || !isCorrectLength(strNumber));
    }


    public boolean isNumber(String str){
        try {
            Integer.parseInt(str);
            return true;
        }
        catch (Exception e){
            System.out.println("This is not number!!");
            return false;
        }
    }

    public boolean isEmpty(String string){
        if (string.isEmpty()){
            System.out.println("This is not number!!");
            return true;
        }
        return false;
    }

    public boolean isCorrectLength(String string){
        if (string.length() > limit){
            System.out.println("This is not correct length!!");
            return false;
        }
        return true;
    }

}
