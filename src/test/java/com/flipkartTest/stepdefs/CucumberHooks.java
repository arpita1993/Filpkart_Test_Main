package com.flipkartTest.stepdefs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CucumberHooks {

    public static WebDriver driver;
    public static final String FLIPKART_HOMEPAGE_URL="https://www.flipkart.com/";

    @Before
    public void setup() {
        System.out.println("before");
        System.setProperty("webdriver.chrome.driver",
                "C:\\temp_files\\Chromedriver\\chromedriver_win32 (1)\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(60L, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        System.out.println("after");
        driver.quit();
    }
}
