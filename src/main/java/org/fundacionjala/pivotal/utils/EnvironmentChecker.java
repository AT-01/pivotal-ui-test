package org.fundacionjala.pivotal.utils;

import org.apache.commons.lang3.SystemUtils;
import java.util.logging.Logger;


/**
 * This class verified in what type of
 * environment the framework is running.
 */
public class EnvironmentChecker {

    private static EnvironmentChecker instance = null;
    private static String osName = "";
    private final static Logger LOG = Logger.getLogger(EnvironmentChecker.class.getName());
    private EnvironmentChecker() {
        osChecker();
    }

    /**
     * this method get class instance.
     *
     * @return class instance
     */
    public static EnvironmentChecker getInstance() {
        if (instance == null) {
            instance = new EnvironmentChecker();
        }
        return instance;
    }

    /***
     * This method will verify the environment OS
     */
    private void osChecker() {
        if (SystemUtils.IS_OS_WINDOWS) {
            LOG.info("Environment Detected: Windows based");
            osName = "Windows";
        } else if (SystemUtils.IS_OS_LINUX) {
            LOG.info("Environment Detected: LINUX Based");
            osName = "Linux";
        } else if(SystemUtils.IS_OS_UNIX) {
            LOG.info("Environment Detected: UNIX Based");
            osName = "Unix";
        } else {
            LOG.info("Environment not supported");
            System.exit(1);
        }
    }

    /***
     * This method will return the OS name
     *
     * @return the OS name
     */
    public String getOsName() {
        return osName;
    }

}
