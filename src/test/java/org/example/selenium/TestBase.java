package org.example.selenium;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import org.example.selenium.helpers.AfterTestExecution;
import org.example.selenium.helpers.FileHelper;
import org.example.selenium.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

@ExtendWith(AfterTestExecution.class)
public class TestBase{

    private static String pathEnvFile = System.getProperty("user.dir") + "/src/test/resources/local_data.json";
    private static ChromeOptions options = new ChromeOptions();
    protected WebDriver driver;

    public static Cookie authCookie;
    public static TakesScreenshot takesScreenshot ;
    JavascriptExecutor js;


    @BeforeAll
    public static void setupAll() throws IOException {
        WebDriver driverBefore;
        String path_ch_driver = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", path_ch_driver + "/chromedriver-linux64/chromedriver");
        //System.setProperty("webdriver.chrome.driver", path_ch_driver + "/chromedriver-mac-arm64/chromedriver");
        //System.out.println(System.getProperty("webdriver.chrome.driver"));
        if ("demo".equals("local")) { // demo stand
            options.setCapability(CapabilityType.BROWSER_VERSION, "127.0.6533.400-beta");
            options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
            options.setCapability(CapabilityType.PLATFORM_NAME, "ANY");
            options.setCapability("se:name", "ololo");
            driverBefore = new RemoteWebDriver(new URL("http://172.17.0.2:4444/wd/hub"), options);
        }
        else if ("local".equals("local")) {
            ObjectMapper mapper = new ObjectMapper();
            HashMap settings = (HashMap) mapper.readValue(new File(pathEnvFile), HashMap.class).get("chrome");
            if (!(boolean) settings.get("headless")){
                options.addArguments("--headless=new");
            }
            options.addArguments("--remote-debugging-port=9222");
//            options.addArguments("--headless=new");
            options.addArguments("--start-maximized");
            options.addArguments("enable-automation");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-extensions");
            options.addArguments("--dns-prefetch-disable");
            options.addArguments("--disable-gpu");
            driverBefore = new ChromeDriver(options);
            }
        else driverBefore = new FirefoxDriver();

        //WebDriver
        driverBefore.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        LoginPage page = new LoginPage(driverBefore).open();
        page.authorization();
        authCookie = page.cookie;
        driverBefore.close();
    }

    @BeforeEach
    @Step("Prepare browser")
    public void setup(){
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
        driver.quit();
    }

    @Step("Make Screen")
    public static File makeScreenshot(String fileName){
        File file = FileHelper.createScreenFile(fileName);
        takesScreenshot.getScreenshotAs(OutputType.FILE).renameTo(file);
        return file;
    }

}
