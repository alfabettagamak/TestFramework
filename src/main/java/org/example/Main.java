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
import java.util.HashMap;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static int some = 0;

    public static void main(String[] args) {

     Runnable action = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++){
                    //System.out.println("i= " + i);
                    some = some + 1;
                }
                System.out.println("SOME " + some);
            }
        };

        Runnable action2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++){
                    //System.out.println("i= " + (100 - i));
                    some = some + 1;
                }
                System.out.println("SOME " + some);
            }
        };
//    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {
//        String path = "C:\\Users\\user\\IdeaProjects\\TestFramework2\\src\\main\\resources\\garage.xml";
//        Garage garage1 = new Garage(path);
//
//        Thread thread = new Thread(action);
//        thread.start();
//
//        Thread thread2 = new Thread(action2);
//        thread2.start();
//
//        NodeList nodes = garage1.document.getElementsByTagName("cars").item(0).getChildNodes();
//        NodeList nodes2 = garage1.document.getElementsByTagName("cars").item(1).getChildNodes();
//        garage1.addNodes(garage1, nodes);
//        garage1.addNodes(garage1, nodes2);
//        System.out.println(garage1.toString());
//        var a = 5;
   }
}