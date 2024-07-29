package org.example.selenium;

import org.example.selenium.helpers.FileHelper;
import org.example.selenium.pages.DashboardPage;
import org.example.selenium.pages.LoginPage;
import org.example.selenium.pages.MainMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

        page.getMenu().directory.click();
        driver.navigate().refresh();
        WebElement element = driver.findElement(By.xpath("//*[text()='-- Select --']"));
        element.click();
        WebElement element1 = driver.findElement(By.xpath("//*[@role='listbox']/div[5]"));
        element1.click();
        Thread.sleep(60000);
    }

    @Test
    public void checkScreenTesting() throws IOException, InterruptedException {
        new DashboardPage(driver).open();
        Thread.sleep(6000);
//        makeScreenshot("expected_dashboard_screen.png");
        File actualScreen = makeScreenshot("dashboard_screen.png");
        File fileExpected = new File(System.getProperty("user.dir") + "/src/test/resources/expected_dashboard_screen.png");
        Boolean result = FileHelper.isEqual(fileExpected, actualScreen);
        Assertions.assertTrue(result);
    }

    @Test
    public void switchTesting() throws InterruptedException {
        new DashboardPage(driver).open();
        String mainWindow = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://google.com");
        driver.switchTo().window(mainWindow);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("alert('!!!!!!!!!!!')");
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        js.executeScript("document.check = prompt('!!!!!!!!!!!')");
        driver.switchTo().alert().sendKeys("SOME TEXT");
        driver.switchTo().alert().accept();
        String text = (String) js.executeScript("return document.check");
        Thread.sleep(6000);
        js.executeScript("document.body.innerHTML = '<h2>' + arguments[0] + '</h2>'", "TtttttTTTTTTGGHJ");
        Thread.sleep(6000);
    }

    @ParameterizedTest
    @CsvSource({"700, 400", "1280, 900"})
    public void windowsSizeTesting(int width, int height) throws InterruptedException {
        new DashboardPage(driver).open();
        driver.manage().window().setSize(new Dimension(width, height));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Boolean isScrollNonExist = (Boolean) js.executeScript(
                "return document.documentElement.scrollWidth==document.documentElement.clientWidth");
        Assertions.assertTrue(isScrollNonExist);
    }


}
