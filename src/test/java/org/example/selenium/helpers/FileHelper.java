package org.example.selenium.helpers;

import io.qameta.allure.Step;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileHelper {

    public static File createScreenFile(String fileName){
        String fileDir = System.getProperty("user.dir") + "/src/test/resources/screens/" + fileName;
        return new File(fileDir);
    }

    @Step("Screen equal")
    public static Boolean isEqual(File expected, File actual) throws IOException {

        Boolean result = true;
        BufferedImage expectedIO = ImageIO.read(expected);
        BufferedImage actualIO = ImageIO.read(actual);

        for (int i = 0; i < expectedIO.getWidth(); i++){
            for (int j = 0; j < expectedIO.getHeight(); j++){
                if (expectedIO.getRGB(i, j) != actualIO.getRGB(i, j)){
                    result = false;
                    Color newColor = new Color(111, 0, 255);
                    actualIO.setRGB(i, j, newColor.getRGB());
                }
            }
        }
        ImageIO.write(actualIO, "png", actual);
        return result;
    }
}
