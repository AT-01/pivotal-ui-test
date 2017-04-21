package org.fundacionjala.pivotal.ui.pages;

import org.fundacionjala.pivotal.ui.pages.common.CommonMethods;
import org.fundacionjala.pivotal.ui.pages.project.ProjectForm;
import org.fundacionjala.pivotal.ui.pages.project.ProjectFormSetting;
import org.fundacionjala.pivotal.ui.pages.project.ProjectManagement;
import org.fundacionjala.pivotal.ui.pages.project.Story;
import org.fundacionjala.pivotal.utils.Environment;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.fundacionjala.pivotal.ui.pages.project.ProjectFormSetting.PROJECT_NAME;
import static org.fundacionjala.pivotal.ui.pages.project.ProjectFormSetting.ACCOUNT;
import static org.fundacionjala.pivotal.ui.pages.project.ProjectFormSetting.PROJECT_PRIVACY;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by bruno on 4/9/2017.
 */
public class StoryDashBoardTest {
    private static final Logger LOGGER = Logger.getLogger(StoryDashBoard.class.getName());
    private Dashboard dashboard;

    /**
     * This before will be executed before a suite.
     */
    @BeforeSuite
    public void beforeSuite() {
        LOGGER.info("inside setup");
        dashboard = SignInForm.loginAsPrimaryUser();
    }

    /**
     * This test will enter to a storyDashBoard.
     */
    @Test
    public void createStory() {
        final String projectNameLink = "template";
        ProjectForm projectForm = dashboard.clickCreateProjectButton();

        Map<ProjectFormSetting, String> newPro = new HashMap<>();
        final String testeandOsd = "TESTEANDOsd";
        newPro.put(PROJECT_NAME, testeandOsd);
        final String cocobongo = Environment.getInstance().getPrimaryUser();
        newPro.put(ACCOUNT, cocobongo);
        final String publicProject = "Public";
        newPro.put(PROJECT_PRIVACY, publicProject);

        projectForm.setConfiguration(newPro);
        ProjectManagement projectManagement = projectForm.clickCreateProjectButton();
        assertEquals(testeandOsd, projectManagement.getProjectName());
        Story story = new Story();
        story.clickAddStoryButton();
        final String storyTitle = "storytest";
        story.setStoryTitle(storyTitle);
        story.clickSaveStory();


    }

    /**
     * This after suit will close the app.
     */
    @AfterSuite
    public void logoutPivotal() {
        CommonMethods.deleteAllProjects();
    }
}
