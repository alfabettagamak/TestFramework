package org.example.selenium;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.example.selenium.pages.LoginPage;
import org.jetbrains.annotations.NotNull;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;

public class TestBase implements TestWatcher {

    protected WebDriver driver;
    public static Cookie authCookie;
    TakesScreenshot takesScreenshot ;
    JavascriptExecutor js;

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String fileNAme =  Long.toString(new Date().getTime());
        makeScreenshot("screen_" + fileNAme +".png");
    }

    @BeforeAll
    public static void setupAll() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        WebDriver driverBefore;
        if (System.getProperty("ENV_VAR") == "demo") {
            driverBefore = new RemoteWebDriver(new URL("http://localhost:444"),options);
        }
        else driverBefore = new ChromeDriver(options);

        //WebDriver
        driverBefore.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driverBefore.manage().window().maximize();

        LoginPage page = new LoginPage(driverBefore).open();
        page.authorization();
        authCookie = page.cookie;
        driverBefore.close();
    }

    @BeforeEach
    @Step("Prepare browser")
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        driver.manage().window().maximize();
        driver.get(LoginPage.url);
        driver.manage().deleteAllCookies();
        driver.manage().addCookie(authCookie);
        this.driver = driver;
        takesScreenshot =  (TakesScreenshot) driver;
        js = (JavascriptExecutor) driver;
    }

    @AfterEach
    public void tearDown(){
        driver.close();
    }

    @Step("Make Screen")
    public File makeScreenshot(String filePath){
        String fileDir = System.getProperty("user.dir") + "/src/test/resources/" + filePath;
        File file = new File(fileDir);
        takesScreenshot.getScreenshotAs(OutputType.FILE).renameTo(file);
        return file;
    }

}
