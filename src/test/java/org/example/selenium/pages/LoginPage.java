package org.example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;
    String url = "https://opensource-demo.orangehrmlive.com/";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage open(){
        driver.get(url);
        return this;
    }

    public DashboardPage authorization(){
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("Admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin123");
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        button.click();
        return new DashboardPage(driver);
    }
}
