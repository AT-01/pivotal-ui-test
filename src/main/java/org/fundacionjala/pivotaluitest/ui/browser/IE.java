package org.fundacionjala.pivotaluitest.ui.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created by HP-PC on 01/11/2016.
 */
public class IE implements Driver {
    /**
     * {@inheritDoc}
     */
    @Override
    public final WebDriver initDriver() {
        return new InternetExplorerDriver();
    }
}
