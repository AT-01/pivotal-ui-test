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
    @BeforeClass
    public void Login() {
        SignInForm signInForm = new SignInForm();
        String user = Environment.getInstance().getPrimaryUser();
        String password = Environment.getInstance().getPrimaryPassword();
        signInForm.loginAs(user, password);
    }

    @org.testng.annotations.Test(priority = 1)
    public void CreateProject() {
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

    @org.testng.annotations.Test(priority = 2)
    public void EditProject() {
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

    @org.testng.annotations.Test(priority = 3)
    public void DeleteProject() {
        ProjectManagement projectManagement = new ProjectManagement();
        projectManagement.scrollDown();
        projectManagement.clickDelete();
        projectManagement.clickDeleteConfirmation();
        Assert.assertEquals(true, projectManagement.deleteMessageConfirmation());
    }
}


