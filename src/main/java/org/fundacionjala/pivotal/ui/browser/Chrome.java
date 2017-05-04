package org.fundacionjala.pivotal.ui.browser;

import org.fundacionjala.pivotal.utils.EnvironmentChecker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.logging.Logger;

/**
 * This class initialize the Chrome Selenium Web Driver.
 */
public class Chrome implements EnvironmentDriver {

    private static final String WEB_DRIVER_PATH_WINDOWS = "drivers/chromedriver.exe";
    private static final String WEB_DRIVER_PATH_LINUX = "drivers/chromeDriver";
    private static final String WEB_DRIVER_KEY = "webdriver.chrome.driver";
    private final static Logger LOG = Logger.getLogger(Chrome.class.getName());

    /**
     * {@inheritDoc}
     */
    @Override
    public WebDriver initDriver() {
        final String webDriverPath = getWebDriverPath();
        System.setProperty(WEB_DRIVER_KEY, webDriverPath);
        return new ChromeDriver();
    }


    /**
     * This method return the webDriver path for each operation system
     *
     * @return webdriver path
     */
    public String getWebDriverPath() {
        final String osName = EnvironmentChecker.getInstance().getOsName();
        switch (osName) {
            case "Windows": {
                return WEB_DRIVER_PATH_WINDOWS;
            }
            case "Linux": {
                return WEB_DRIVER_PATH_LINUX;
            }
            default: {
                LOG.info("Web driver path was not found for " + osName);
                System.exit(1);
                return "";
            }
        }
    }
}
