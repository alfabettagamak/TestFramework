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
import java.time.Duration;
import java.util.List;

import static io.qameta.allure.SeverityLevel.CRITICAL;

public class SeleniumExample extends TestBase {

    @RepeatedTest(1)
    @DisplayName("Dashboard Page")
    //@Description("This test attempts to log into the website using a login and a password. Fails if any error happens.\n\nNote that this test does not test 2-Factor Authentication.")
    //@Severity(CRITICAL)
    //@Owner("John Doe")
//    @Link(name = "Website", url = "https://dev.example.com/")
//    @Issue("AUTH-123")
//    @TmsLink("TMS-456")
    public void dashboardTesting() throws FileNotFoundException {
        DashboardPage page = new DashboardPage(driver).open();

        List<WebElement> list = driver.findElements(By.xpath(
                "//*[text()=\"Quick Launch\"]/../../../div/div[" +
                        "@class=\"oxd-grid-3 orangehrm-quick-launch\"]/div"));
        List<WebElement> list1 = driver.findElements(By.xpath("//ul[@class='oxd-main-menu']/li"));
        Allure.step("Asserts");
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
    }

    @Test
    public void checkLogout() throws InterruptedException {
        DashboardPage page = new DashboardPage(driver);
        page.open().getMenu().directory.click();
        driver.navigate().refresh();
        WebElement element = driver.findElement(By.xpath(
                "//div[contains(@class, 'context')]//h5[contains(@class, 'table')]"));
        WebElement dropdown = driver.findElement(By.xpath(
                "//div[contains(@class, 'header')]//span[contains(@class, 'userdropdown')]" +
                        "//i[contains(@class, 'userdropdown')]"));
        dropdown.click();
        WebElement logout = driver.findElement(By.xpath(
                "//ul[contains(@class, 'dropdown')]//*[text()='Logout']"));
        logout.click();
        WebElement loginTitle = driver.findElement(By.xpath(
                "//div[contains(@class, 'login')]//h5[contains(@class, 'login')]"));
        Assert.assertTrue(loginTitle.isDisplayed());
    }

    @Test
    public void checkLogout2() throws InterruptedException {
        DashboardPage page = new DashboardPage(driver);
        page.open().getMenu().directory.click();
        driver.navigate().refresh();
        WebElement element = driver.findElement(By.xpath(
                "//div[contains(@class, 'context')]//h5[contains(@class, 'table')]"));
        WebElement loginTitle = page.getMenu().LogOut(driver);
        Assert.assertTrue(loginTitle.isDisplayed());
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
    public void checkScreenTesting() throws IOException, InterruptedException {
        new DashboardPage(driver).open();
//        makeScreenshot("expected_dashboard_screen.png");
        File actualScreen = makeScreenshot("dashboard_screen.png");
        File fileExpected = new File(System.getProperty("user.dir") + "/src/test/resources/expected_dashboard_screen.png");
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
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("alert('!!!!!!!!!!!')");
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        js.executeScript("document.check = prompt('!!!!!!!!!!!')");
        driver.switchTo().alert().sendKeys("SOME TEXT");
        driver.switchTo().alert().accept();
        String text = (String) js.executeScript("return document.check");
        js.executeScript("document.body.innerHTML = '<h2>' + arguments[0] + '</h2>'", "TtttttTTTTTTGGHJ");
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
