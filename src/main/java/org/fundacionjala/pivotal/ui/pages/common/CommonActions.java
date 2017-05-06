package org.fundacionjala.pivotal.ui.pages.common;

import org.fundacionjala.pivotal.ui.browser.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * This is class wait to load the element.
 */
public final class CommonActions {

    /**
     * Constructor private.
     */
    private CommonActions() {
    }

    /**
     * This method waits and clear the element.
     *
     * @param webElement Element to wait and clear.
     */
    public static void clearTextField(final WebElement webElement) {
        DriverManager.getInstance().getWait().until(ExpectedConditions.visibilityOf(webElement));
        webElement.clear();
    }

    /**
     * This method waits and fill the element.
     *
     * @param webElement Element to wait and fill.
     * @param text       text to fill.
     */
    public static void sendKeys(final WebElement webElement, final String text) {
        DriverManager.getInstance().getWait().until(ExpectedConditions.visibilityOf(webElement));
        webElement.clear();
        webElement.sendKeys(text);
    }

    /**
     * This method waits and clickElement the element.
     *
     * @param webElement Element to wait and clickElement.
     */
    public static void clickElement(final WebElement webElement) {
        DriverManager.getInstance().getWait().until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    /**
     * This method waits and clickElement the element.
     *
     * @param webElement Element to wait and clickElement.
     * @return text to element.
     */
    public static String getText(final WebElement webElement) {
        DriverManager.getInstance().getWait().until(ExpectedConditions.visibilityOf(webElement));
        return webElement.getText();
    }

    /**
     * This method set a text field.
     *
     * @param webElement UI element
     * @param text plain text
     */
    public static void setTextField(WebElement webElement, final String text) {
        clearTextField(webElement);
        sendKeys(webElement, text);
    }


}
