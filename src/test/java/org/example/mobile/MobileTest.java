package org.example.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


public class MobileTest {

    // Node.js
    // npm
    // nmp install -g appium
    // npm install wd (web driver)
    // android studio ANDROID_HOME

    private AppiumDriver driver;
    private AppiumDriverLocalService service;

    @BeforeEach
    public void beforeTest(){
        serverStart();
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
        service.close();
        service.stop();
    }

    @Test
    public void exampleTesting() throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:deviceName", "Medium_Phone_API_35");
        capabilities.setCapability("appium:app", "/Users/alisa_school/java_lessons/TestFramework/TestFramework/example.apk");
        capabilities.setCapability("appium:automationName", "uiautomator2");
        // wd/hub
        driver = new AndroidDriver(new URL("http://localhost:4723/"), capabilities);
        System.out.println(driver.getPageSource());
        driver.findElement(By.xpath("//*[@text='Ask mom for help']")).click();
        driver.findElement(By.xpath("//*[@text='Ask mom for help']"))
                .getAttribute("checked").equals(true);
    }

    @Test
    public void chromeTEsting() throws InterruptedException, MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:deviceName", "Medium_Phone_API_35");
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "127.0.6533.103");
        capabilities.setCapability("appium:automationName", "uiautomator2");

        driver = new AndroidDriver(new URL("http://localhost:4723/"), capabilities);

        driver.get("https://wikipedia.org");
        Thread.sleep(6000);

    }


    public void serverStart(){
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(4723);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }
}
