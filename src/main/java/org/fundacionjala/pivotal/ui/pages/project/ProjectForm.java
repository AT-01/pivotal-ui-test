package org.fundacionjala.pivotal.ui.pages.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.fundacionjala.pivotal.ui.pages.AbstractBasePage;
import org.fundacionjala.pivotal.ui.pages.common.CommonActions;

import static org.fundacionjala.pivotal.ui.pages.project.ProjectFormSetting.ACCOUNT;
import static org.fundacionjala.pivotal.ui.pages.project.ProjectFormSetting.PROJECT_NAME;
import static org.fundacionjala.pivotal.ui.pages.project.ProjectFormSetting.PROJECT_PRIVACY;

/**
 * This class is Project form to PivotalTracker.
 */

public class ProjectForm extends AbstractBasePage {
    @FindBy(css="button[class=\"button button--action\"]")
    private WebElement createProjectButton;
    @FindBy(css="input[type=\"text\"]")
    private WebElement projectNameTextField;
    @FindBy(css="div[class=\"tc-account-selector\"]")
    private WebElement dropdownAccountButton;//this one may not belong here
    @FindBy(css="span[class=\"tc-account-selector__create-account-text\"]")
    private WebElement createAccountButtonOptionDropDownList;
    @FindBy(css="input[class=\"tc-account-creator__name\"]")
    private WebElement newAccountProjectTextField;
    @FindBy(css="input[value=\"public\"]")
    private WebElement radioButtonPublic;
    @FindBy(css="button[type=\"submit\"]")
    private WebElement saveNewProjectButton;

    /**
     * This method set the projectName in the text field.
     *
     * @param projectName String whit the projectName.
     */
    public void setProjectNameTextField(final String projectName) {
        CommonActions.clearTextField(projectNameTextField);
        CommonActions.sendKeys(projectNameTextField, projectName);
    }

    /**
     * This method set the Account in the text field or DropDownList.
     *
     * @param account That is to search.
     */
    public void setAccountDropDownList(final String account) {
        CommonActions.clickElement(dropdownAccountButton);
        List<WebElement> menuBodyList = driver.findElements(By.xpath("//div[contains(text(), '" + account + "')]"));
        if (menuBodyList.isEmpty()) {
            CommonActions.clickElement(createAccountButtonOptionDropDownList);
            CommonActions.clearTextField(newAccountProjectTextField);
            CommonActions.sendKeys(newAccountProjectTextField, account);
        } else {
            final int index = 0;
            CommonActions.clickElement(menuBodyList.get(index));
        }
    }

    /**
     * This method click on the new project button.
     *
     * @return {@link ProjectManagement}
     */
    public ProjectManagement clickCreateProjectButton() {
        CommonActions.clickElement(createProjectButton);
        return new ProjectManagement();
    }
    public ProjectManagement clickCreateButton() {
        CommonActions.clickElement(saveNewProjectButton);
        return new ProjectManagement();
    }

    /**
     * This method selected privacy project.
     *
     * @param projectPrivacyType return project privacy type.
     */
    public void selectedProjectPrivacy(final ProjectPrivacy projectPrivacyType) {
        WebElement projectPrivacy =
                driver.findElement(By.cssSelector("input[value='" + projectPrivacyType.toString() + "']"));
        CommonActions.clickElement(projectPrivacy);
    }

    /**
     * Executes the configurations sent.
     *
     * @param configureMap is a map that contains the configurations.
     */
    public void setConfiguration(final Map<ProjectFormSetting, String> configureMap) {
        Map<ProjectFormSetting, ProjectFormStep> strategyOption = strategyConfigureSetting(configureMap);
        Set<ProjectFormSetting> keys = configureMap.keySet();
        for (ProjectFormSetting key : keys) {
            strategyOption.get(key).executeStep();
        }
    }

    /**
     * Create an strategy steps configuration options filling a map with
     * all the existing configurations.
     *
     * @param configureMap is a map that contains all the configurations.
     * @return the configure map with strategies.
     */
    private Map<ProjectFormSetting, ProjectFormStep> strategyConfigureSetting(
            final Map<ProjectFormSetting, String> configureMap) {
        Map<ProjectFormSetting, ProjectFormStep> strategyMap = new HashMap<>();
        strategyMap.put(PROJECT_NAME, () -> setProjectNameTextField(configureMap.get(PROJECT_NAME)));
        strategyMap.put(ACCOUNT, () -> setAccountDropDownList(configureMap.get(ACCOUNT)));
        strategyMap.put(PROJECT_PRIVACY,
                () -> selectedProjectPrivacy(ProjectPrivacy.valueOf(configureMap.get(PROJECT_PRIVACY).toUpperCase())));
        return strategyMap;
    }
}
