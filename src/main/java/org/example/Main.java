package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) {
        System.out.println("% " + possibility());
        int [] array = {1, 2, 3, 3, 4, 3, 5, 5, 7, 8, 9, 9 , 9 , 3};
        ArrayList arrayList = new ArrayList<>();
        for (int i = 0; i < array.length; i++){
            for (int j = i + 1 ; j < array.length; j++){
                //System.out.println(array[i] + "==" +  array[j] + ", i = " + i + " j = " + j);
                if (array[i] == array[j]) arrayList.add(array[i]);
            }
        }
        System.out.println(arrayList);
        HashSet <Integer> someHash = new HashSet<>();
        HashSet <Integer> duplicates = new HashSet<>();

        for (int number: array){
            if (!someHash.add(number)){
                duplicates.add(number);
            }
        }
        System.out.println("duplicate " + duplicates);



        int aa = 10;
        int bb = 5;

        aa = aa + bb; // 15
        bb = aa - bb; // 10
        aa = aa - bb; // 5

        System.out.println(isSimple(2));
        String n;
   }

   public static String possibility(){
       Random rnd = new Random();
       int number = rnd.nextInt(100);
       if (number <= 25) return "25%";
       if (number > 25 && number <= 90) return "65%";
       return "10%";
   }

   public void board(){
       String [][] board = new String[8][8]; // {{1, 2, 5}, {2, 4, 2} ,{1,1,1}}
       var b = 5;
       for (int i =0; i < board.length; i++){
           for (int j = 0; j < board[0].length; j++){
               if ((i + j) % 2 == 0) board[i][j] = "W";
               else board[i][j] = "B";
           }
       }
       for (String [] array : board){
           for (String string: array){
               System.out.print(string + " ");
           }
           System.out.println();
       }
   }

   public static boolean isSimple(int number){
       for( int i = 2; i <= Math.sqrt(number); i++){
           if (number % i == 0){
               return false;
           }
       }
       return true;
   }

   public int factorial(int num){
        if (num == 0) return 1;
        return num * factorial(num - 1);
   }

   public void maxString(){
       // max value
       char max = '0';
       String str = "xgfhd2ghg9shsthsh5hgfhgfhs9sre";
       for (char a: str.toCharArray()){
           if (a > max) max = a;
       }
       System.out.println("max " + max);
   }


}