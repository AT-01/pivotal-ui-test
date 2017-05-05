package org.fundacionjala.pivotal.ui.pages.common;

import org.fundacionjala.pivotal.api.ApiUtils;
import org.fundacionjala.pivotal.ui.browser.DriverManager;
import org.fundacionjala.pivotal.utils.Environment;

/**
 * This class contains all commons methods.
 */
public final class CommonNavigator {

    private static final String DASHBOARD = "/dashboard";

    private static final String PROJECT_DASHBOARD = "/n/projects";

    /**
     * Constructor private.
     */
    private CommonNavigator() {
    }

    /**
     * This method go to dashboard page.
     */
    public static void goToDashboard() {
        goToUrl(DASHBOARD);
    }

    /**
     * @param projectName The project name.
     */
    public static void goToProjectDashboard(String projectName) {
        final String projectId = ApiUtils.getProjectID(projectName);
        goToUrl(PROJECT_DASHBOARD + "/" + projectId);
    }

    /**
     * @param relativeUrl The relative url
     */
    public static void goToUrl(String relativeUrl) {
        final String url = String.format("%s%s", Environment.getInstance().getBaseUrl(), relativeUrl);

        DriverManager.getInstance().getDriver().navigate().refresh();
        DriverManager.getInstance().getDriver().navigate().to(url);
    }
}
