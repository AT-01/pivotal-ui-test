package org.fundacionjala.pivotal.ui.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * This class initialize the Chrome Selenium Web Driver.
 */
public class Chrome implements Driver {

    private static final String WEB_DRIVER_PATH = "drivers/chromedriver.exe";

    private static final String WEB_DRIVER_KEY = "webdriver.chrome.driver";

    /**
     * {@inheritDoc}
     */
    @Override
    public WebDriver initDriver() {
        System.setProperty(WEB_DRIVER_KEY, WEB_DRIVER_PATH);
        return new ChromeDriver();
    }
}
