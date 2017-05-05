package project;

import org.fundacionjala.pivotal.ui.pages.SignInForm;
import org.fundacionjala.pivotal.ui.pages.project.ProjectForm;
import org.fundacionjala.pivotal.ui.pages.project.ProjectManagement;
import org.fundacionjala.pivotal.ui.pages.project.ProjectPrivacy;
import org.fundacionjala.pivotal.utils.Environment;
import static org.junit.Assert.assertEquals;


import org.testng.annotations.BeforeClass;

/**
 * Created by Alejandro Alcocer on 4/10/2017.
 */
public class ProjectTests {
    static final int PRIORITY_ONE = 1;
    static final int PRIORITY_TWO = 2;
    static final int PRIORITY_THREE = 3;
    static final String PROJECT_NAME = "";
    static final String ACCOUNT_NAME = "";
    static final String NEW_PROJECT_NAME = "";
    static final String NEW_DESCRIPTION_NAME = "";

    /**
     * login to pivotal tracker.
     */
    @BeforeClass
    public void login() {
        SignInForm signInForm = new SignInForm();
        String user = Environment.getInstance().getPrimaryUser();
        String password = Environment.getInstance().getPrimaryPassword();
        signInForm.loginAs(user, password);
    }

    /**
     * creates a new project.
     */
    @org.testng.annotations.Test(priority = PRIORITY_ONE)
    public void createProject() {
        //when
        ProjectForm projectForm = new ProjectForm();
        ProjectManagement projectManagement = new ProjectManagement();
        projectForm.clickCreateProjectButton();
        projectForm.setProjectNameTextField(PROJECT_NAME);
        projectForm.setAccountDropDownList(ACCOUNT_NAME);
        projectForm.selectedProjectPrivacy(ProjectPrivacy.PUBLIC);
        projectForm.clickCreateButton();
        String projectNameOnPivotal = projectManagement.getProjectName();
        //then
        assertEquals(PROJECT_NAME, projectNameOnPivotal);

    }
    /**
     * edit the project.
     */
    @org.testng.annotations.Test(priority = PRIORITY_TWO)
    public void editProject() {
        //when
        ProjectManagement projectManagement = new ProjectManagement();
        projectManagement.goToSettings(projectManagement.getProjectId());
        projectManagement.setNewProjectName(NEW_PROJECT_NAME);
        projectManagement.setNewProjectDescription(NEW_DESCRIPTION_NAME);
        projectManagement.clickSave();
        //then
        assertEquals(NEW_PROJECT_NAME, projectManagement.getNewProjectName());
    }
    /**
     * delete the project.
     */
    @org.testng.annotations.Test(priority = PRIORITY_THREE)
    public void deleteProject() {
        ProjectManagement projectManagement = new ProjectManagement();
        projectManagement.scrollDown();
        projectManagement.clickDelete();
        projectManagement.clickDeleteConfirmation();
        assertEquals(true, projectManagement.deleteMessageConfirmation());
    }
}


