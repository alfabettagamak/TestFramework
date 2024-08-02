package org.example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DirectoryPage extends BasePage {


    String url = "https://opensource-demo.orangehrmlive.com/web/index.php/directory/viewDirectory";

    public DirectoryPage(WebDriver driver) {
        super(driver);
    }

    public DirectoryPage open(){
        driver = super.open(url);
        return this;
    }

    public WebElement getDirectoryElement(){
        WebElement element = driver.findElement(By.xpath(
                "//div[contains(@class, 'context')]//h5[contains(@class, 'table')]"));
        return element;
    }

}
