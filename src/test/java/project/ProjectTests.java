package project;

import org.fundacionjala.pivotal.ui.pages.SignInForm;
import org.fundacionjala.pivotal.ui.pages.project.ProjectForm;
import org.fundacionjala.pivotal.ui.pages.project.ProjectPrivacy;
import org.fundacionjala.pivotal.utils.Environment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Alejandro Alcocer on 4/10/2017.
 */
public class ProjectTests {
    @Before
    public void Login() {
        SignInForm signInForm = new SignInForm();
        String user = Environment.getInstance().getPrimaryUser();
        String password = Environment.getInstance().getPrimaryPassword();
        signInForm.loginAs(user, password);
    }

    @Test
    public void CreateProject() {
        //when
        String projectName = Environment.getInstance().getProjectName();
        String accountName = Environment.getInstance().getAccountName();

        //
        ProjectForm projectForm = new ProjectForm();
        projectForm.clickCreateProjectButton();
        projectForm.setProjectNameTextField(projectName);
        projectForm.setAccountDropDownList(accountName);
        projectForm.selectedProjectPrivacy(ProjectPrivacy.PUBLIC);
        projectForm.clickCreateButton();
        //then

    }
}

