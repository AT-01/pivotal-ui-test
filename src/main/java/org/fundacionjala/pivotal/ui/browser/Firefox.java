package org.fundacionjala.pivotal.ui.browser;

import org.fundacionjala.pivotal.utils.Constants;
import org.fundacionjala.pivotal.utils.EnvironmentChecker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.logging.Logger;

/**
 * This class initialize the Firefox Selenium Web Driver.
 */
public class Firefox implements EnvironmentDriver {

    private static final String WEB_DRIVER_PATH_WINDOWS = "drivers/geckodriver.exe";
    private static final String WEB_DRIVER_PATH_LINUX = "drivers/geckodriver";
    private static final String WEB_DRIVER_KEY = "webdriver.gecko.driver";
    private static final Logger LOG = Logger.getLogger(Firefox.class.getName());

    /**
     * {@inheritDoc}
     */
    @Override
    public WebDriver initDriver() {
        final String webDriverPath = getWebDriverPath();
        System.setProperty(WEB_DRIVER_KEY, webDriverPath);
        return new FirefoxDriver();
    }


    @Override
    public String getWebDriverPath() {
        final String osName = EnvironmentChecker.getInstance().getOsName();
        switch (osName) {
            case Constants.WINDOWS_OS:
                return WEB_DRIVER_PATH_WINDOWS;
            case Constants.LINUX_OS:
                return WEB_DRIVER_PATH_LINUX;
            default:
                LOG.info("Web driver path was not found for " + osName);
                throw new RuntimeException("Environment not supported");
        }
    }
}
