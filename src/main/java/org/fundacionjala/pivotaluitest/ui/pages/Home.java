package org.fundacionjala.pivotaluitest.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.fundacionjala.pivotaluitest.ui.pages.project.ProjectForm;
import org.fundacionjala.pivotaluitest.ui.utils.WaitElement;

/**
 * Home page the PivotalTracker.
 */
public class Home extends AbstractBasePage {
    @FindBy(id = "create_new_project_button")
    private WebElement createProjectButton;

    /**
     * This method a click "Create Project" button.
     * @return ProjectForm page.
     */
    public ProjectForm clickCreateProjectButton() {
        WaitElement.waitClick(createProjectButton);
        return new ProjectForm();
    }
}
