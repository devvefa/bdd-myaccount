package com.hemlak.base;
import com.thoughtworks.gauge.BeforeScenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

public class DriverFactory {
    public static ThreadLocal<WebDriver> webdriver = new ThreadLocal<>();
    private final BaseTest webDriverFactory = new BaseTest();

    public WebDriverWait driverWait;
    @BeforeScenario
    public void setUpBrowser(){
        webDriverFactory.setUp();
        webdriver.set(webDriverFactory.getDriver());

    }

    public static WebDriver getDriver(){
        return webdriver.get();
    }
}
