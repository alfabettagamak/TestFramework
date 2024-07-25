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
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumExample extends TestBase {

    @Test
    public void firstTesting() throws InterruptedException {
        isNeedAuth = true;
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboard = loginPage
                .open()
                .authorization();
        Assertions.assertEquals(2, dashboard.getDashboardsElements().size());
//        WebElement img = driver.findElement(By.xpath("//img[@alt='company-branding']"));
//        Assertions.assertTrue(img.isEnabled());
    }

    @Test
    public void dashboardTesting(){
        DashboardPage page = new DashboardPage(driver);
        page.open();
        List<WebElement> list = driver.findElements(By.xpath(
                "//*[text()=\"Quick Launch\"]/../../../div/div[" +
                        "@class=\"oxd-grid-3 orangehrm-quick-launch\"]/div"));
        List<WebElement> list1 = driver.findElements(By.xpath("//ul[@class='oxd-main-menu']/li"));
        Assertions.assertEquals(6, list.size());
        Assertions.assertEquals(12, list1.size());
    }

    @Test
    public void dashboardMenuTesting() throws InterruptedException {
        DashboardPage page = new DashboardPage(driver);
        page.open().getMenu().time.click();

//        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
//        wait.until(driver -> driver.findElement(By.xpath("//span[text()='Time']")));

        driver.get(driver.getCurrentUrl());
        Thread.sleep(60000);
    }
}
