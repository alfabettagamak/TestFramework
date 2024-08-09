package org.example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainMenu {

    WebDriver driver;

    @FindBy(xpath = "//ul[@class='oxd-main-menu']/li[1]" )
    private WebElement admin;

    @FindBy(xpath = "//ul[@class='oxd-main-menu']/li[2]" )
    private WebElement pim;

    @FindBy(xpath = "//ul[@class='oxd-main-menu']/li[3]" )
    private WebElement leave;

    @FindBy(xpath = "//ul[@class='oxd-main-menu']/li[4]" )
    private WebElement time;

    @FindBy(xpath = "//ul[@class='oxd-main-menu']/li[9]" )
    private WebElement directory;


    public MainMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getAdmin() {
        return admin;
    }

    public WebElement getPim() {
        return pim;
    }

    public WebElement getLeave() {
        return leave;
    }

    public WebElement getTime() {
        return time;
    }

    public WebElement getDirectory() {
        return directory;
    }

    public MainMenu clickDirectoryMenu() {
        directory.click();
        driver.navigate().refresh();
        return this;
    }

    public LoginPage logout(){
        WebElement dropdown = driver.findElement(By.xpath(
                "//div[contains(@class, 'header')]//span[contains(@class, 'userdropdown')]" +
                        "//i[contains(@class, 'userdropdown')]"));
        dropdown.click();
        WebElement logout = driver.findElement(By.xpath(
                "//ul[contains(@class, 'dropdown')]//*[text()='Logout']"));
        logout.click();
        return new LoginPage(driver);
    }
}
