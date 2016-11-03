package org.fundacionjala.pivotaluitest.ui.browser;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fundacionjala.pivotaluitest.utils.Environment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * This class initialize the Remote Selenium Web Driver.
 */
class RemoteBrowser implements Driver {
    private static final Logger LOGGER = LogManager.getLogger(RemoteBrowser.class);

    private static final Environment ENVIRONMENT = Environment.getInstance();

    private static final String HTTPS_PROXY_HOST = "https.proxyHost";

    private static final String HTTPS_PROXY_PORT = "https.proxyPort";

    private static final String BROWSER = "browser";

    private static final String BROWSER_VERSION = "browser_version";

    private static final String OS = "os";

    private static final String OS_VERSION = "os_version";

    private static final String RESOLUTION = "resolution";

    private static final String BROWSER_STACK_DEBUG = "browserstack.debug";

    private static final String BUILD = "build";

    /**
     * {@inheritDoc}
     */
    @Override
    public WebDriver initDriver() {
        final String url =
                "https://" + Environment.getInstance().getBrowserStackUser() + ":"
                        + Environment.getInstance().getBrowserStackKey()
                        + "@hub-cloud.browserstack.com/wd/hub";
        DesiredCapabilities caps = new DesiredCapabilities();
        if (!ENVIRONMENT.getProxy().isEmpty()) {
            System.getProperties().put(HTTPS_PROXY_HOST, ENVIRONMENT.getHost());
            System.getProperties().put(HTTPS_PROXY_PORT, ENVIRONMENT.getPort());
        }
        caps.setCapability(BROWSER, ENVIRONMENT.getBrowserCapability());
        caps.setCapability(BROWSER_STACK_DEBUG, ENVIRONMENT.getStackDebug());
        caps.setCapability(BUILD, ENVIRONMENT.getBuild());
        caps.setCapability(BROWSER_VERSION, ENVIRONMENT.getBrowserVersion());
        caps.setCapability(OS, ENVIRONMENT.getOS());
        caps.setCapability(OS_VERSION, ENVIRONMENT.getOSVersion());
        caps.setCapability(RESOLUTION, ENVIRONMENT.getResolution());
        try {
            return new RemoteWebDriver(new URL(url), caps);
        } catch (MalformedURLException e) {
            LOGGER.warn(e.getMessage(), e);
        }
        return null;
    }
}
