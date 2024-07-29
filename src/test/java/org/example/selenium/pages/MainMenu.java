package org.example.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainMenu {

    WebDriver driver;

    public MainMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='oxd-main-menu']/li[1]" )
    public WebElement admin;

    @FindBy(xpath = "//ul[@class='oxd-main-menu']/li[2]" )
    public WebElement pim;

    @FindBy(xpath = "//ul[@class='oxd-main-menu']/li[3]" )
    public WebElement leave;

    @FindBy(xpath = "//ul[@class='oxd-main-menu']/li[4]" )
    public WebElement time;

    @FindBy(xpath = "//ul[@class='oxd-main-menu']/li[9]" )
    public WebElement directory;

}
