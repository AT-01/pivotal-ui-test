package org.fundacionjala.pivotal.ui.pages;
import org.fundacionjala.pivotal.api.ApiUtils;
import org.fundacionjala.pivotal.ui.pages.common.CommonMethods;
import org.fundacionjala.pivotal.ui.pages.common.CommonNavigator;
import org.fundacionjala.pivotal.ui.pages.common.UICommonMethods;
import org.fundacionjala.pivotal.ui.pages.project.ProjectForm;
import org.fundacionjala.pivotal.ui.pages.project.ProjectFormSetting;
import org.fundacionjala.pivotal.ui.pages.project.ProjectManagement;
import org.fundacionjala.pivotal.ui.pages.project.Story;
import org.fundacionjala.pivotal.utils.Environment;
import org.testng.annotations.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import static org.fundacionjala.pivotal.ui.pages.project.ProjectFormSetting.ACCOUNT;
import static org.fundacionjala.pivotal.ui.pages.project.ProjectFormSetting.PROJECT_NAME;
import static org.fundacionjala.pivotal.ui.pages.project.ProjectFormSetting.PROJECT_PRIVACY;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertEquals;

/**
 * @author bsronald on 5/4/17.
 */
public class TasksTest {
    private static final Logger LOGGER = Logger.getLogger(StoryDashBoard.class.getName());
    private Dashboard dashboard;
    private Map<ProjectFormSetting, String> newProject;
    private String testeandOsd;
    private String taskName;
    private Story story;

    /**
     * This before will be executed before a suite.
     */
    @BeforeClass
    public void setupSuite() {
        LOGGER.info("inside setup");

        // set variables
        testeandOsd = "TESTEANDOsd";
        final String publicProject = "Public";
        final String cocobongo = Environment.getInstance().getPrimaryUser();

        // set variables into map in order to create a project
        newProject = new HashMap<>();
        newProject.put(PROJECT_NAME, testeandOsd);
        newProject.put(ACCOUNT, cocobongo);
        newProject.put(PROJECT_PRIVACY, publicProject);

        // Login
        dashboard = new Dashboard(); //SignInForm.loginAsPrimaryUser();

    }

    /**
     * This method is executed before each test case.
     */
    @BeforeMethod
    public void beforeTest() {
        // Local variables
        final String storyTitle = "storytest";
        taskName = "TestingTask";


        // Given
        ProjectForm projectForm = dashboard.clickCreateProjectButton();
        projectForm.setConfiguration(newProject);
        ProjectManagement projectManagement = projectForm.clickCreateProjectButton();
        final String projectID = ApiUtils.getProjectID(testeandOsd);
        LOGGER.info(" " + projectID);

        // When
        assertEquals(projectManagement.getProjectName(), testeandOsd);
        story = new Story();
        story.clickAddStoryButton();
        story.setStoryTitle(storyTitle);
        story.addTask(taskName);

    }


    /**
     * This test will create a task.
     */
    @Test
    public void createTaskTest() {

        //Then
        assertTrue(story.isTaskNamePresent(taskName));
        story.clickSaveStory();
    }

    /**
     * This test delete a task.
     */
    @Test
    public void deleteTaskTest() {

        //When
        assertTrue(story.isTaskNamePresent(taskName));
        story.deleteTask(taskName);

        //Then
        assertFalse(story.isTaskDeleted(taskName));
        story.clickSaveStory();


    }

    /**
     * This test is executed after each test.
     */
    @AfterMethod
    public void deleteProjects() {
        CommonMethods.deleteAllProjects();
        CommonNavigator.goToDashboard();
    }

//    /**
//     * This method is executed after all suite.
//     */
//    @AfterClass
//    public void tearDown() {
//        UICommonMethods.closeBrowser();
//    }
}
