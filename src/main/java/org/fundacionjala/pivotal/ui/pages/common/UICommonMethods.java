package org.fundacionjala.pivotal.ui.pages.common;

import org.fundacionjala.pivotal.ui.browser.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.logging.Logger;

/**
 * UI common methods.
 */
public final class UICommonMethods {
    private static WebDriver driver = DriverManager.getInstance().getDriver();
    private static final Logger LOGGER = Logger.getLogger(UICommonMethods.class.getName());
    private static final int TIME_TO_SLEEP = 300;

    /**
     * Private Constructor.
     */
    private UICommonMethods() { }

    /**
     * This method try to find a web element in the web page.
     *
     * @param by element to search
     * @return true if the element is found and false if this
     * is not founded
     */
    public static boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * This method mouve mouse over a webElement.
     *
     * @param by By that locates an element
     */
    public static void moveMouseOver(By by) {
        if (waitElementIsPresent(2, by)) {
            Actions action = new Actions(driver);
            action.moveToElement(driver.findElement(by)).click().build().perform();
        } else {
            System.out.println("It was not able to move the mouse over an element");
        }
    }

    /**
     * Close the browser.
     */
    public static void closeBrowser() {
        driver.quit();
    }


    /**
     * This method make a refresh.
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }

    /**
     * The driver wait to a element disappear.
     *
     * @param maxCount number of times to wait
     * @param element  to search
     * @return true or false
     */
    @SuppressWarnings("SameParameterValue")
    public static boolean waitElementIsNotPresent(int maxCount, By element) {
        boolean result = true;
        int count = 1;
        try {
            LOGGER.info("Wait Element is not present ->" + element.toString());
            while (result && count <= maxCount) {
                Thread.sleep(TIME_TO_SLEEP);
                result = isElementPresent(element);
                count++;
            }
        } catch (InterruptedException e) {
            LOGGER.info("Exception Element is not Present" + e);
        }
        return !result;
    }

    /**
     * The driver wait to find a element.
     *
     * @param maxCount number of times to wait
     * @param by       to search
     * @return true or false
     */
    public static boolean waitElementIsPresent(int maxCount, By by) {
        boolean result = false;
        int count = 1;
        LOGGER.info("Wait Element is present ->" + by.toString());
        while (!result && count <= maxCount) {
            result = isElementPresent(by);
            count++;
        }
        return result;
    }
}
