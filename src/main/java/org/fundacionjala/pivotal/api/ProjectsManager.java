package org.fundacionjala.pivotal.api;

import io.restassured.response.Response;
import org.fundacionjala.pivotal.utils.Constants;

/**
 *
 */
public final class ProjectsManager {
    /**
     * Private constructor.
     */
    private ProjectsManager() {
    }

    /**
     * @param projectName The project name.
     * @return The response object.
     */
    public static Response post(final String projectName) {
        final String projectJsonTemplate =
                "{\"name\":\"%s\",\"project_type\":\"private\",\"new_account_name\":\"a003\"}";

        return RequestManager.post(Constants.PROJECTS_ENDPOINT, String.format(projectJsonTemplate, projectName));
    }
}
