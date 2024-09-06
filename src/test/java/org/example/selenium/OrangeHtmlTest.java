    package org.example.selenium;

import io.qameta.allure.*;
import org.example.selenium.helpers.FileHelper;
import org.example.selenium.pages.DashboardPage;
import org.example.selenium.pages.DirectoryPage;
import org.example.selenium.pages.LoginPage;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;

import java.io.*;
import java.util.List;

public class OrangeHtmlTest extends TestBase {

    @RepeatedTest(1)
    @DisplayName("Dashboard Page Menu")
    //@Description("This test attempts to log into the website using a login and a password. Fails if any error happens.\n\nNote that this test does not test 2-Factor Authentication.")
    //@Severity(CRITICAL)
    //@Owner("John Doe")
//    @Link(name = "Website", url = "https://dev.example.com/")
//    @Issue("AUTH-123")
//    @TmsLink("TMS-456")
    public void dashboardMenuTesting() throws FileNotFoundException {
        new DashboardPage(driver).open();
        List<WebElement> listMenu = driver.findElements(By.xpath("//ul[@class='oxd-main-menu']/li"));
        Allure.step("Asserts");
        Assertions.assertEquals(12, listMenu.size());
    }

    @Test
    public void clickMenuTesting() throws InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(driver).open();
//        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
//        wait.until(driver -> driver.findElement(By.xpath("//span[text()='Time']")));
        DirectoryPage directoryPage = dashboardPage.clickDirectoryMenu();
        directoryPage.selectRandomJob();
        Assertions.assertTrue(driver.findElement(By.xpath("//h6[text()='Directory']")).isDisplayed());
    }

    @Test
    public void checkLogout() throws InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage
                .open()
                .getMenu()
                .clickDirectoryMenu()
                .logout();

        WebElement loginTitle = driver.findElement(By.xpath(
                "//div[contains(@class, 'login')]//h5[contains(@class, 'login')]"));
        loginTitle.sendKeys(Keys.ENTER);
        Assert.assertTrue(loginTitle.isDisplayed());
    }

    @Test
    public void checkLogout2() throws InterruptedException {
        LoginPage loginPage = new DashboardPage(driver)
                .open()
                .getMenu()
                .clickDirectoryMenu()
                .logout();

        Assert.assertTrue(loginPage.getLoginTitle().isDisplayed());
    }

    @Test
    public void checkSearchTesting() throws InterruptedException {
        DirectoryPage page = new DirectoryPage(driver);
        page.open();
//        WebElement element = driver.findElement(By.xpath(
//                "//*[text()=\"Job Title\"]//..//..//div[contains(@class, 'oxd-select-text--after')]"));
//        element.click();
//        List <WebElement> list = driver.findElements(By.xpath("//div[contains(@role, 'listbox')]//li"));
//        var a=5;
        WebElement recordsFoundLabel = driver.findElement(By.xpath(
                "//div[contains(@class, 'orangehrm-corporate-directory')]//span[contains(@class, 'oxd-text oxd-text--span')]"));
        String resultText = recordsFoundLabel.getText();
        WebElement element = driver.findElement(By.xpath("//*[text()='-- Select --']"));
        element.click();
        WebElement element1 = driver.findElement(By.xpath("//*[@role='listbox']/div[5]"));
        element1.click();
        WebElement button = driver.findElement(By.xpath("//button[contains(@class, 'left')]"));
        button.click();
        WebElement newResult = driver.findElement(By.xpath(
                "//div[contains(@class, 'orangehrm-corporate-directory')]//span[contains(@class, 'oxd-text oxd-text--span')]"));
        String newResultText = newResult.getText();
        Assert.assertNotEquals(resultText, newResultText);
    }


    @Test
    public void screenTesting() throws IOException, InterruptedException {
        new DashboardPage(driver).open();
        //makeScreenshot("expected_dashboard_screen.png");
        File actualScreen = makeScreenshot("dashboard_screen.png");
        File fileExpected = new File(System.getProperty("user.dir") + "/src/test/resources/screens/expected_dashboard_screen.png");
        Allure.addAttachment("actual", new FileInputStream(actualScreen));
        Allure.addAttachment("expected", new FileInputStream(fileExpected));
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
        js.executeScript("alert('!!!!!!!!!!!')");
        // Thread.sleep(2000);
        driver.switchTo().alert().accept();
        // Thread.sleep(2000);
        js.executeScript("document.check = prompt('!!!!!!!!!!!')");
        String promptText = "SOME TEXT";
        driver.switchTo().alert().sendKeys(promptText);
        driver.switchTo().alert().accept();
        String actualText = (String) js.executeScript("return document.check");
        Assertions.assertEquals(promptText, actualText);
        js.executeScript("document.body.innerHTML = '<h2>' + arguments[0] + '</h2>'", "TtttttTTTTTTGGHJ");
    }

    @ParameterizedTest
    @CsvSource({"700, 400", "1280, 900"})
    public void windowsSizeTesting(int width, int height) throws InterruptedException {
        new DashboardPage(driver).open();
        driver.manage().window().setSize(new Dimension(width, height));
        Boolean isScrollNonExist = (Boolean) js.executeScript(
                "return document.documentElement.scrollWidth==document.documentElement.clientWidth");
        Assertions.assertTrue(isScrollNonExist);
    }

}
