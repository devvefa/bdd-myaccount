package com.hemlak.base;

import com.thoughtworks.gauge.AfterScenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {


    public WebDriver driver;
    ChromeOptions chromeOptions = new ChromeOptions();


    public void setUp() {
        String baseUrl = "https://betagiris.hepsiemlak.com/giris-yap";
        WebDriverManager.chromedriver().setup();

        chromeOptions.setExperimentalOption("w3c", false);


        chromeOptions.addArguments("--start-fullscreen");
//        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--enable-strict-powerful-feature-restrictions");
        driver = new ChromeDriver(chromeOptions);

        driver.get(baseUrl);

    }




    public WebDriver getDriver() {
        return driver;
    }

    @AfterScenario
    public void tearDown() {
        if (DriverFactory.getDriver() == null) {
            return;
        }
        DriverFactory.getDriver().quit();

    }
}

