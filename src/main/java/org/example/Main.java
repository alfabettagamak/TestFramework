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
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {


//        String path = "/Users/alisa_school/java_lessons/TestFramework/TestFramework/src/main/resources/garage.xml";
//        Garage garage1 = new Garage(path);
//        NodeList nodes = garage1.document.getElementsByTagName("cars").item(0).getChildNodes();
//
//        for (int i = 0; i < nodes.getLength(); i++) {
//            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
//                HashMap hashMap = new HashMap<>();
//                hashMap.put("name", nodes.item(i).getNodeName());
//                for (int j = 0; j < nodes.item(i).getAttributes().getLength(); j++) {
//                    String key = nodes.item(i).getAttributes().item(j).getNodeName();
//                    String value = nodes.item(i).getAttributes().item(j).getNodeValue();
//                    hashMap.put(key, value);
//                }
//                garage1.garage.add(hashMap);
//            }
//            var a = 5;
//        }
    }
}