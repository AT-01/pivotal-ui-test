package org.fundacionjala.pivotal.ui.pages;

import org.fundacionjala.pivotal.api.ApiUtils;
import org.fundacionjala.pivotal.api.RequestManager;
import org.fundacionjala.pivotal.ui.pages.common.CommonMethods;
import org.fundacionjala.pivotal.ui.pages.common.CommonNavigator;
import org.fundacionjala.pivotal.utils.Constants;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 *
 */
public class StoryTasksTest {
    private static final String PROJECT_NAME = "TestProject001";

    private static final String STORY_NAME = "TestStory001";

    private StoryDashBoard storyDashboard;

    /**
     * This before will be executed before a suite.
     */
    @BeforeSuite
    public void beforeSuite() {
        SignInForm.loginAsPrimaryUser();

        RequestManager.post(
                Constants.PROJECTS_ENDPOINT,
                String.format(
                        "{\"name\":\"%s\",\"project_type\":\"private\",\"new_account_name\":\"a003\"}",
                        PROJECT_NAME));
        final String projectId = ApiUtils.getProjectID(PROJECT_NAME);
        RequestManager.post(
                String.format(Constants.STORIES_ENDPOINT, projectId),
                String.format("{\"name\":\"%s\"}", STORY_NAME));
    }

    /**
     *
     */
    @BeforeMethod
    public void beforeMethod() {
        CommonNavigator.goToProjectDashboard(PROJECT_NAME);
        storyDashboard = new StoryDashBoard();
        storyDashboard.clickForStoryDeploy(STORY_NAME);
    }

    /**
     * Create story task test.
     */
    @Test
    public void createStoryTaskTest() {
        final String taskText = "TaskText001";

        storyDashboard.setTaskText(taskText);

        assertTrue(storyDashboard.taskTextExist(taskText));
    }

    /**
     * Update story task test.
     */
    @Test
    public void updateStoryTaskTest() {
        final String taskText = "TaskText002";
        storyDashboard.setTaskText(taskText);

        final String updatedTaskText = "UpdatedTaskText002";
        WebElement taskElement = storyDashboard.getTaskElementByText(taskText);
        storyDashboard.updateTheTask(taskElement, updatedTaskText);

        assertTrue(storyDashboard.taskTextExist(updatedTaskText));
    }

    /**
     * Delete story task test.
     */
    @Test
    public void deleteStoryTaskTest() {
        final String taskText = "TaskText003";
        storyDashboard.setTaskText(taskText);

        storyDashboard.deleteTask(taskText);

        assertFalse(storyDashboard.taskTextExist(taskText));
    }

    /**
     * After suite.
     */
    @AfterSuite
    public void afterSuite() {
        CommonMethods.deleteAllProjects();
    }
}
