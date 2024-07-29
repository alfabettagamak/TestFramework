package org.example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DashboardPage extends BasePage {


    String url = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage open(){
        driver = super.open(url);
        return this;
    }

    public List<WebElement> getDashboardsElements(){
        List<WebElement> elements = driver.findElements(By.xpath("//*[text()='Dashboard']"));
        return elements;
    }

}
