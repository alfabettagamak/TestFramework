package org.example.selenium.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;
    protected MainMenu menu;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.menu = new MainMenu(driver);
    }

    public MainMenu getMenu() {
        return menu;
    }
}
