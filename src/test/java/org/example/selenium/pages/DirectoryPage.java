package org.example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Random;

public class DirectoryPage extends BasePage {


    String url = "https://opensource-demo.orangehrmlive.com/web/index.php/directory/viewDirectory";

    public DirectoryPage(WebDriver driver) {
        super(driver);
    }

    public DirectoryPage open(){
        driver = super.open(url);
        return this;
    }

    public WebElement getDirectoryElement(){
        WebElement element = driver.findElement(By.xpath(
                "//div[contains(@class, 'context')]//h5[contains(@class, 'table')]"));
        return element;
    }

    public DirectoryPage selectRandomJob() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//*[text()='-- Select --']"));
        element.click();
        List<WebElement> elements = driver.findElements(By.xpath("//*[@role='listbox']/div"));
        Random random = new Random();
        int randElement = random.nextInt(elements.size());
        new Actions(driver)
                .scrollToElement(driver.findElement(By.xpath("//*[@role='listbox']/div["+ randElement + "]" )))
                .perform();
        driver.findElement(By.xpath("//*[@role='listbox']/div["+ randElement + "]")).click();
        return this;
    }

}
