package com.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreditTest {

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "/Applications/Firefox.app/Contents/MacOS/firefox");
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("http://www.iteye.com/login");
    }
}
