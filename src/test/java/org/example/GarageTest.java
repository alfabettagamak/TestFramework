package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

public class GarageTest {

    @Test
    public void storeTesting(){
        Date date = new Date();
        String path = "/Users/alisa_school/java_lessons/TestFramework/TestFramework/src/main/resources/garage_" + date.getTime() + ".xml";
        Garage garage = new Garage();
        try {
            garage.store(path);
        } catch (Exception e) {
            System.out.println("Some error " + e.getMessage());
            Assertions.assertTrue(false);
        }
        Assertions.assertTrue(Files.exists(Path.of(path)));
    }
}
