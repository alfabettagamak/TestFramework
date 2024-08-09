package org.example.selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected MainMenu menu;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.menu = new MainMenu(driver);
    }

    @Step("Get main Menu")
    public MainMenu getMenu() {
        return menu;
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    @Step("Open page")
    public WebDriver open(String url){
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
//        wait.until(
//                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        wait.until(webDriver -> webDriver.findElement(By.xpath("//h6")).isDisplayed());
        return driver;
    }
}
