package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Map;



public class GarageTest {

    @Test
    public void storeTesting(){
        Date date = new Date();
        String path = "C:\\Users\\user\\IdeaProjects\\TestFramework2\\src\\main\\resources\\garage_" + date.getTime() + ".xml";
        Garage garage = new Garage();
        try {
            garage.store(path);
        } catch (Exception e) {
            System.out.println("Some error " + e.getMessage());
            Assertions.assertTrue(false);
        }
        Assertions.assertTrue(Files.exists(Path.of(path)));
    }

    @Test
    public void HashMapToFileTesting() throws ParserConfigurationException, IOException, SAXException {
        Date date = new Date();
        String path = "C:\\Users\\user\\IdeaProjects\\TestFramework2\\src\\main\\resources\\garage_" + date.getTime() + ".xml";
        Garage garage = new Garage();
        try {
            garage.store(path);
        } catch (Exception e) {
            System.out.println("Some error " + e.getMessage());
            Assertions.fail();
        }
        Garage garage1 = new Garage(path);
        NodeList nodes = garage1.document.getElementsByTagName("CAR").item(0).getChildNodes();
        garage1.addNodes(garage1, nodes);
        Assertions.assertEquals(garage.getsize(), garage1.getsize());
        for (int i=0; i < garage.getsize(); i++) {
            Assertions.assertEquals(garage.get(i), garage1.get(i));
        }

        //var a = 5;
    }
}
