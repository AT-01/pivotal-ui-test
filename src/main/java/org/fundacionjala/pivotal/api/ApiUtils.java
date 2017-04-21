package org.fundacionjala.pivotal.api;

import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

/**
 * Created by Bruno Barrios on 4/12/2017.
 */
public final class ApiUtils {

    /**
     * Constructor private.
     */
    private ApiUtils() {
    }

    /**
     * This method will be used to get the ProjectID Based on the project name.
     * @param projectName to search.
     * @return projectID.
     */
    public static String getProjectID(final String projectName) {
        Response response = RequestManager.get("/projects");
        List<Map<String, ?>> projectList = response.jsonPath().get("");
        String projectId = projectList.stream()
                .filter(project -> String.valueOf(project.get("name")).equalsIgnoreCase(projectName))
                .findAny()
                .get()
                .get("id").toString();
        return projectId;
    }

}
