package org.example.selenium.helpers;

import io.qameta.allure.Allure;
import org.example.selenium.TestBase;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;


import java.io.File;
import java.io.FileInputStream;
import java.util.Date;


public class AfterTestExecution implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        if (extensionContext.getExecutionException().isPresent()) {
            String name =  Long.toString(new Date().getTime());
            File screen = TestBase.makeScreenshot("screen_" + name +".png");
            Allure.addAttachment("fail screen", new FileInputStream(screen));
        }
    }
}
