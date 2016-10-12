package org.fundacionjala.pivotaluitest.cucumber.stepdefinition.ui.project;

import cucumber.api.java.en.When;

import org.fundacionjala.pivotaluitest.ui.pages.Home;
import org.fundacionjala.pivotaluitest.ui.pages.project.ProjectForm;
import org.fundacionjala.pivotaluitest.ui.pages.project.ProjectManagement;

/**
 * This class is manage the project step.
 */
public class ProjectStep {
    private static final String PROJECT_NAME = "Automation Project Test";
    private Home homePage;

    /**
     * Constructor the ProjectStep Class.
     * @param otherHome Home page.
     */
    public ProjectStep(final Home otherHome){
        homePage = otherHome;
    }

    /**
     * This method added a new project.
     */
    @When("^I added a new project$")
    public void iAddedANewProject() {
        ProjectForm projectForm = homePage.clickCreateProjectButton();
        projectForm.setProjectNameTextField(PROJECT_NAME);
        projectForm.setAccountDropDownList();
        ProjectManagement projectManagement = projectForm.clickCreateProjectButton();
    }
}
