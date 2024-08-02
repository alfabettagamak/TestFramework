package org.example.selenium;

import org.example.selenium.pages.DashboardPage;
import org.example.selenium.pages.LoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AuthTest {
    static WebDriver driver = new ChromeDriver();

    @AfterAll
    public static void tearDown(){
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
