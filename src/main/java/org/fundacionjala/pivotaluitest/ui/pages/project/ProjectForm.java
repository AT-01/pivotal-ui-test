package org.fundacionjala.pivotaluitest.ui.pages.project;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import org.fundacionjala.pivotaluitest.ui.pages.AbstractBasePage;
import org.fundacionjala.pivotaluitest.ui.utils.WaitElement;

/**
 * This class is Project form to PivotalTracker.
 */
public class ProjectForm extends AbstractBasePage {
    private static final String ACCOUNT_AUTOMATION_TEST = "AutomationTestAT01";
    @FindBy(className = "tc-project-name__input")
    private WebElement projectNameTextField;
    @FindBy(className = "tc-account-selector")
    private WebElement accountDropDownList;
    @FindBy(className = "tc-account-selector__option-account-name")
    private WebElement accountOptionDropDownList;
    @FindBy(css = "button.tc-create-project-footer__button.tc-create-project-footer__button--submit")
    private WebElement createButton;
    @FindBy(css = "div[data-aid='create-account-button']")
    private WebElement createAccountButtonOptionDropDownList;
    @FindBy(css = "input.tc-account-creator__name")
    private WebElement newAccountProjectTextField;
    @FindBys({
            @FindBy(xpath = "//div[contains(text(), '" + ACCOUNT_AUTOMATION_TEST + "')]")
    })
    private List<WebElement> menuBodyList;

    /**
     * This method set the projectName in the text field.
     *
     * @param projectName String whit the projectName.
     */
    public void setProjectNameTextField(final String projectName) {
        WaitElement.waitClear(projectNameTextField);
        WaitElement.waitSendKeys(projectNameTextField, projectName);
    }

    /**
     * This method set the Account in the text field or DropDownList.
     */
    public void setAccountDropDownList() {
        WaitElement.waitClick(accountDropDownList);
        if (menuBodyList.isEmpty()) {
            WaitElement.waitClick(createAccountButtonOptionDropDownList);
            WaitElement.waitClear(newAccountProjectTextField);
            WaitElement.waitSendKeys(newAccountProjectTextField, ACCOUNT_AUTOMATION_TEST);
        } else {
            final int index = 0;
            WaitElement.waitClick(menuBodyList.get(index));
        }
    }

    /**
     * This method a new project the application.
     *
     * @return Project Management page.
     */
    public ProjectManagement clickCreateProjectButton() {
        WaitElement.waitClick(createButton);
        return new ProjectManagement();
    }
}
