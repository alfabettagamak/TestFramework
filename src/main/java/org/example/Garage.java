package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class Garage {

    public ArrayList<HashMap> garage = new ArrayList<>();
    Document document;

    Garage(String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        document = documentBuilder.parse(path);

        //String id = document.getElementsByTagName("cars").item(0).getAttributes().getNamedItem("id").getNodeValue();
    }

    Garage(){
        HashMap<String, String> bmw = new HashMap<>();
        bmw.put("name", "bmw");
        bmw.put("model", "x6");
        bmw.put("year", "2012");
        bmw.put("number", "H345XC198");

        HashMap<String, String> haval = new HashMap<>();
        haval.put("name", "haval");
        haval.put("model", "ZR");
        haval.put("year", "2022");
        haval.put("number", "H323XC23");

        HashMap<String, String> haval2 = new HashMap<>();
        haval2.put("name", "haval");
        haval2.put("model", "HW");
        haval2.put("year", "2018");
        haval2.put("number", "H325XC55");
        garage.add(bmw);
        garage.add(haval);
        garage.add(haval2);
    }

    public int getsize (){
                return garage.size();
    };

    public HashMap get(int index){
        return garage.get(index);
    }

    private Document getDocument() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        return document;
    }

    public void store(String path) throws ParserConfigurationException, IOException, TransformerException {
        document = getDocument();
        Element car = document.createElement("CAR");
        car.setAttribute("id", "1");
        document.appendChild(car);

        for( HashMap<String, String> item: garage){
            Element garageElement = document.createElement(item.get("name"));
            for (String key: item.keySet()){
                if (key.equals("name")) continue;
                else garageElement.setAttribute(key, item.get(key));
            }
            car.appendChild(garageElement);
        }
        save(path);
    }

    public void save(String path) throws TransformerException, IOException {
        TransformerFactory transformerFactory = TransformerFactory.newDefaultInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource src = new DOMSource(document);
        OutputStream outputStream = Files.newOutputStream(Path.of(path));
        transformer.transform(src, new StreamResult(outputStream));
        outputStream.close();
    }

    public Garage addNodes (Garage garage, NodeList nodes) throws ParserConfigurationException, IOException, SAXException {

        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                HashMap hashMap = new HashMap<>();
                hashMap.put("name", nodes.item(i).getNodeName());
                for (int j = 0; j < nodes.item(i).getAttributes().getLength(); j++) {
                    String key = nodes.item(i).getAttributes().item(j).getNodeName();
                    String value = nodes.item(i).getAttributes().item(j).getNodeValue();
                    hashMap.put(key, value);
                }
                garage.garage.add(hashMap);
            }
        }
        return garage;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this) return true;
        return false;
    }

}
