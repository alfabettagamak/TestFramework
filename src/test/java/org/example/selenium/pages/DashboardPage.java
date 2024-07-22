package org.example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DashboardPage {


    private WebDriver driver;
    String url = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getDashboardsElements(){
        List<WebElement> elements = driver.findElements(By.xpath("//*[text()='Dashboard']"));
        return elements;
    }

}
