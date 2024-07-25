package org.example.selenium;

import org.example.selenium.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TestBase {

    protected WebDriver driver;
    public static Cookie authCookie;
    static protected boolean isNeedAuth = true;

    @BeforeAll
    public static void setupAll(){
        WebDriver driverBefore = new ChromeDriver();
        driverBefore.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driverBefore.manage().window().maximize();
        LoginPage page = new LoginPage(driverBefore).open();
        page.authorization();
        authCookie = page.cookie;
        driverBefore.close();
    }

    @BeforeEach
    public void setup(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.manage().window().maximize();
        if (isNeedAuth){
            driver.get(LoginPage.url);
            driver.manage().deleteAllCookies();
            driver.manage().addCookie(authCookie);
        }
        this.driver = driver;
    }

    @AfterEach
    public void tearDown(){
        driver.close();
    }

}
