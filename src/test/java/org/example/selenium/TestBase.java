package org.example.selenium;

import org.example.selenium.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;

public class TestBase {

    protected WebDriver driver;
    public static Cookie authCookie;
    static protected boolean isNeedAuth = true;
    TakesScreenshot takesScreenshot ;

    @BeforeAll
    public static void setupAll(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        WebDriver driverBefore = new ChromeDriver(options);
        driverBefore.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driverBefore.manage().window().maximize();
        LoginPage page = new LoginPage(driverBefore).open();
        page.authorization();
        authCookie = page.cookie;
        driverBefore.close();
    }

    @BeforeEach
    public void setup(){
        ChromeOptions options = new ChromeOptions();
       // options.addArguments("--headless=new");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.manage().window().maximize();
        if (isNeedAuth){
            driver.get(LoginPage.url);
            driver.manage().deleteAllCookies();
            driver.manage().addCookie(authCookie);
        }
        this.driver = driver;
        takesScreenshot =  (TakesScreenshot) driver;
    }

    @AfterEach
    public void tearDown(){
        driver.close();
    }

    public File makeScreenshot(String filePath){
        String fileDir = System.getProperty("user.dir") + "/src/test/resources/" + filePath;
        File file = new File(fileDir);
        takesScreenshot.getScreenshotAs(OutputType.FILE).renameTo(file);
        return file;
    }
}
