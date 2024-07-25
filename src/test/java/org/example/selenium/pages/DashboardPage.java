package org.example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DashboardPage extends BasePage {


    String url = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage open(){
        driver.get(url);
        return this;
    }

    public List<WebElement> getDashboardsElements(){
        List<WebElement> elements = driver.findElements(By.xpath("//*[text()='Dashboard']"));
        return elements;
    }

}
