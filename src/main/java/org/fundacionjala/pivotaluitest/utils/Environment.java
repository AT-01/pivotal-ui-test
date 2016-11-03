package org.fundacionjala.pivotaluitest.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * This class is in charge to set the default  parameters to
 * establishes the connection with pivotal tracker.
 */
public final class Environment {

    private static final Logger LOGGER = LogManager.getLogger(Environment.class);

    private static final String CONFIG = "gradle.properties";

    private static final String AUTHENTICATION_TOKEN = "token";

    private static final String AUTHENTICATION_PROXY = "proxy";

    private static final String AUTHENTICATION_BASE_URI = "authentication.baseURI";

    private static final String PRIMARY_AUTHENTICATION_USER = "primaryAuthenticationUser";

    private static final String PRIMARY_AUTHENTICATION_PASS = "primaryAuthenticationPassword";

    private static final String AUTHENTICATION_BASE_URL = "authentication.baseURL";

    private static final String AUTHENTICATION_BROWSER = "authenticationBrowser";

    private static final String BROWSER = "browser";

    private static final String BROWSER_VERSION = "browser_version";

    private static final String OS = "os";

    private static final String OS_VERSION = "os_version";

    private static final String RESOLUTION = "resolution";

    private static final String BROWSER_STACK_DEBUG = "browserstackDebug";

    private static final String BUILD = "builded";

    private static final String PORT = "port";

    private static final String HOST = "host";

    private static final String TIMEOUT = "timeout";
    public static final String BROWSER_STACK_USER = "browserStackUser";
    public static final String BROWSER_STACK_KEY = "browserStackKey";

    private static Environment environment;

    private Properties properties;

    /**
     * This method read the config property file.
     */
    private Environment() {
        File file = new File(CONFIG);
        try (FileReader fileReader = new FileReader(file)) {
            properties = new Properties();
            properties.load(fileReader);
        } catch (FileNotFoundException e) {
            LOGGER.warn("The properties file couldn't be found", e);
        } catch (IOException e) {
            LOGGER.warn("A problem of type", e);
        }
    }

    /**
     * This method Instance the environment if this does not exist.
     *
     * @return the instanced Environment.
     */
    public static Environment getInstance() {
        if (environment == null) {
            environment = new Environment();
        }
        return environment;
    }

    /**
     * Get the properties of the file.
     *
     * @param env string name of property setting.
     * @return String that is a property
     */
    private String getEnv(final String env) {
        String property = System.getProperty(env);
        if (property == null) {
            return properties.getProperty(env);
        }
        return property;
    }

    /**
     * Get the base url of pivotal tracker.
     *
     * @return String uri.
     */
    public String getBaseUri() {
        return getEnv(AUTHENTICATION_BASE_URI);
    }

    /**
     * Get the proxy.
     *
     * @return String with the configured proxy.
     */
    public String getProxy() {
        return getEnv(AUTHENTICATION_PROXY);
    }

    /**
     * Get the configured token.
     *
     * @return String with the configured token.
     */
    public String getToken() {
        return getEnv(AUTHENTICATION_TOKEN);
    }

    /**
     * Get the timeout.
     *
     * @return String with the configure timeout.
     */
    public int getTimeout() {
        return Integer.parseInt(getEnv(TIMEOUT));
    }

    /**
     * Get the timeout.
     *
     * @return String with the configure timeout.
     */
    public String getBrowser() {
        return getEnv(AUTHENTICATION_BROWSER);
    }

    /**
     * Return the user name.
     *
     * @return String whit the name user
     */
    public String getPrimaryUser() {
        return getEnv(PRIMARY_AUTHENTICATION_USER);
    }

    /**
     * return the password.
     *
     * @return String whit the password
     */
    public String getPrimaryPassword() {
        return getEnv(PRIMARY_AUTHENTICATION_PASS);
    }

    /**
     * Get the base url of pivotal tracker.
     *
     * @return String url.
     */
    public String getBaseUrl() {
        return getEnv(AUTHENTICATION_BASE_URL);
    }

    /**
     * Get the browser stack user.
     *
     * @return String browser stack user.
     */
    public String getBrowserStackUser() {
        return getEnv(BROWSER_STACK_USER);
    }

    /**
     * Get the browser stack key.
     *
     * @return String browser stack key.
     */
    public String getBrowserStackKey() {
        return getEnv(BROWSER_STACK_KEY);
    }

    /**
     * Get the browser for remote configuration.
     *
     * @return String browser.
     */
    public String getBrowserCapability() {
        return getEnv(BROWSER);
    }

    /**
     * Get the permission of debug.
     *
     * @return true or false.
     */
    public String getStackDebug() {
        return getEnv(BROWSER_STACK_DEBUG);
    }

    /**
     * Get the build name.
     *
     * @return String build name.
     */
    public String getBuild() {
        return getEnv(BUILD);
    }

    /**
     * Get the operative system.
     *
     * @return String operative system name.
     */
    public String getOS() {
        return getEnv(OS);
    }

    /**
     * Get the operative system version.
     *
     * @return String operative system version.
     */
    public String getOSVersion() {
        return getEnv(OS_VERSION);
    }

    /**
     * Get the browser version configuration.
     *
     * @return String browser version.
     */
    public String getBrowserVersion() {
        return getEnv(BROWSER_VERSION);
    }

    /**
     * Get the resolution for remote configuration.
     *
     * @return String resolution.
     */
    public String getResolution() {
        return getEnv(RESOLUTION);
    }

    /**
     * Get the proxy port.
     *
     * @return String proxy port.
     */
    public Object getPort() {
        return getEnv(PORT);
    }

    /**
     * Get the proxy host.
     *
     * @return String proxy host.
     */
    public Object getHost() {
        return getEnv(HOST);
    }
}
