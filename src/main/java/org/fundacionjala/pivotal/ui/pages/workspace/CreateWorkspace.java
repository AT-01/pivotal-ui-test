package org.fundacionjala.pivotal.ui.pages.workspace;

import org.fundacionjala.pivotal.ui.pages.AbstractBasePage;
import org.fundacionjala.pivotal.ui.pages.Steps;
import org.fundacionjala.pivotal.ui.pages.common.CommonActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represent the create a work space.
 */
public class CreateWorkspace extends AbstractBasePage {

    @FindBy(css = "label ~ input.tc-form__input")
    private WebElement workspaceNameTextField;

    @FindBy(css = "button[type='submit']")
    private WebElement createWorkspaceLink;

    @FindBy(css = ".tc_button.tc_button_cancel")
    private WebElement cancelCreateWorkspaceLink;

    @FindBy(className = "tc_modal_content")
    private WebElement getCreateWorkspaceContainer;

    @FindBy(className = "tc_form_error_message")
    private WebElement messageErrorNameEmpty;

    /**
     * This method to do click on the button "Create Workspace" on the Form.
     *
     * @return a object Workspaces page.
     */
    public Workspace clickCreateWorkspaceLink() {
        CommonActions.clickElement(createWorkspaceLink);
        return new Workspace();
    }

    /**
     * Method to do click on the button "Cancel" on the Form.
     */
    public void clickCancelCreateWorkspaceLink() {
        cancelCreateWorkspaceLink.click();
    }

    /**
     * Method that to insert a name into Workspace Name field.
     *
     * @param workspaceName a string with name to write.
     * @return CreateWorkspace.
     */
    public CreateWorkspace setWorkspaceName(final String workspaceName) {
        workspaceNameTextField.sendKeys(workspaceName);
        return this;
    }

    /**
     * Method that to permit set values to create a new Workspace.
     *
     * @param values a map to set of the strategy
     * @return a Map with the values of the workspace created.
     */
    public Map<WorkspaceSteps, Steps> getStrategyStepMap(final Map<WorkspaceSteps, Object> values) {
        final Map<WorkspaceSteps, Steps> strategyMap = new HashMap<>();
        strategyMap.put(WorkspaceSteps.WORKSPACE_NAME,
                () -> setWorkspaceName(String.valueOf(values.get(WorkspaceSteps.WORKSPACE_NAME))));
        return strategyMap;
    }

    /**
     * Method that get a message when a workspace try to be created with empty name.
     *
     * @return a message of error.
     */
    public String messageError() {
        return messageErrorNameEmpty.getText();
    }

}
