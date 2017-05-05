package org.fundacionjala.pivotal.api;

import io.restassured.response.Response;
import org.fundacionjala.pivotal.utils.Constants;

/**
 *
 */
public final class StoriesManager {

    /**
     * Private constructor.
     */
    private StoriesManager() {
    }

    /**
     * @param projectName The project name.
     * @param storyName The story name.
     * @return The response object.
     */
    public static Response post(final String projectName, final String storyName) {
        final String projectId = ApiUtils.getProjectID(projectName);
        return RequestManager.post(
                String.format(Constants.STORIES_ENDPOINT, projectId),
                String.format("{\"name\":\"%s\"}", storyName));
    }
}
