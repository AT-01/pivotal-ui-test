package org.fundacionjala.pivotal.ui.pages.project;

import org.fundacionjala.pivotal.ui.pages.AbstractBasePage;
import org.fundacionjala.pivotal.ui.pages.common.CommonActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Bruno Barrios on 4/11/2017.
 */
public class SettingsProject extends AbstractBasePage {
    @FindBy(css = "project_name")
    private WebElement projectName;

    @FindBy(css = "save_bar__submit")
    private WebElement save;

    /**
     * This method will edit project title.
     *
     * @param projectTitle new Project title.
     */
    public void ediProjectName(final String projectTitle) {
        CommonActions.clickElement(projectName);
        CommonActions.sendKeys(projectName, projectTitle);
    }

    /**
     * This method will save edit project.
     *
     * @return ProjectManagement.
     */
    public ProjectManagement saveEditProject() {
        CommonActions.clickElement(save);
        return new ProjectManagement();
    }
}
