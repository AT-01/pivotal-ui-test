package project;

import org.fundacionjala.pivotal.ui.pages.SignInForm;
import org.fundacionjala.pivotal.ui.pages.project.ProjectForm;
import org.fundacionjala.pivotal.ui.pages.project.ProjectManagement;
import org.fundacionjala.pivotal.ui.pages.project.ProjectPrivacy;
import org.fundacionjala.pivotal.utils.Environment;
import org.junit.Assert;

import org.testng.annotations.BeforeClass;

/**
 * Created by Alejandro Alcocer on 4/10/2017.
 */
public class ProjectTests {
    static final int PRIORITY_ONE = 1;
    static final int PRIORITY_TWO = 2;
    static final int PRIORITY_THREE = 3;

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
        String projectName = Environment.getInstance().getProjectName();
        String accountName = Environment.getInstance().getAccountName();
        ProjectForm projectForm = new ProjectForm();
        ProjectManagement projectManagement = new ProjectManagement();
        projectForm.clickCreateProjectButton();
        projectForm.setProjectNameTextField(projectName);
        projectForm.setAccountDropDownList(accountName);
        projectForm.selectedProjectPrivacy(ProjectPrivacy.PUBLIC);
        projectForm.clickCreateButton();
        String projectNameOnPivotal = projectManagement.getProjectName();
        //then
        Assert.assertEquals(projectName, projectNameOnPivotal);

    }
    /**
     * edit the project.
     */
    @org.testng.annotations.Test(priority = PRIORITY_TWO)
    public void editProject() {
        //when
        String newProjectName = Environment.getInstance().getProjectNewName();
        String newDescription = Environment.getInstance().getDescriptionName();
        ProjectManagement projectManagement = new ProjectManagement();
        projectManagement.goToSettings(projectManagement.getProjectId());
        projectManagement.setNewProjectName(newProjectName);
        projectManagement.setNewProjectDescription(newDescription);
        projectManagement.clickSave();
        //then
        Assert.assertEquals(newProjectName, projectManagement.getNewProjectName());
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
        Assert.assertEquals(true, projectManagement.deleteMessageConfirmation());
    }
}


