package org.example.selenium;

import org.example.selenium.pages.DashboardPage;
import org.example.selenium.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class SeleniumExample {
    WebDriver driver;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.manage().window().setSize(new Dimension(1800, 1200));
    }

    @AfterEach
    public void tearDown(){
        driver.close();
    }

    @Test
    public void firstTesting() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboard = loginPage
                .open()
                .authorization();
        Assertions.assertEquals(2, dashboard.getDashboardsElements().size());

//        WebElement img = driver.findElement(By.xpath("//img[@alt='company-branding']"));
//        Assertions.assertTrue(img.isEnabled());
    }
}
