package org.fundacionjala.pivotal.ui.pages.project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fundacionjala.pivotal.ui.browser.DriverManager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.fundacionjala.pivotal.ui.pages.AbstractBasePage;
import org.fundacionjala.pivotal.ui.pages.common.CommonActions;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * This Class is Project Management.
 */
public class ProjectManagement extends AbstractBasePage {
    @FindBy(css = "span.raw_context_name")
    private WebElement projectNameSpan;
    @FindBy(css = "input[id=\"project_name\"]")
    private WebElement projectNameTextField;
    @FindBy(css = "input[id=\"project_description\"]")
    private WebElement projectDescription;
    @FindBy (css = "input[class=\"save_bar__submit\"]")
    private WebElement saveButton;
    @FindBy (css = "span._1q50y__TrackerProjectHeader__projectName")
    private WebElement newProjectName;
    @FindBy (css = "a[id=\"delete_link\"]")
    private WebElement deleteLink;
    @FindBy (css = "input[id=\"confirm_delete\"]")
    private WebElement confirmDelete;
    @FindBy (css = "li[id=\"notice\"]")
    private  WebElement deleteConfirmationPopUp;


    @FindBy(css = "a[data-aid='navTab-settings'] span")
    private WebElement navBarSettings;

    @FindBy(css = "a[data-aid='navTab-stories']")
    private WebElement staorytab;

    @FindBy(css = "project_name")
    private WebElement projectName;

    @FindBy(css = "save_bar__submit")
    private WebElement save;

    /**
     * this project obtain project name.
     *
     * @return project name.
     */
    public String getProjectName() {
        return CommonActions.getText(projectNameSpan);
    }


    /**
     * this project obtain project name in the settings page.
     *
     * @return project name.
     */
    public String getNewProjectName() {
        return CommonActions.getText(newProjectName);
    }

    /**
     * this project obtain project id.
     *
     * @return Project id of created new project.
     */
    public String getProjectId() {
        String projectId = "";
        String regularExpression = "(\\d+)$";
        String url = driver.getCurrentUrl();
        Matcher matcher = Pattern.compile(regularExpression).matcher(url);
        if (matcher.find()) {
            projectId = matcher.group();
        }
        return projectId;
    }

    /**
     * it goes to the pivotal tracker page.
     * @param accountToSet is used.
     */
    public void goToSettings(final String accountToSet) {
        driver.navigate().to("https://www.pivotaltracker.com/projects/" + accountToSet + "/settings");
    }

    /**
     * set a new project name.
     * @param newProjectName is returned.
     */
    public void setNewProjectName(final String newProjectName) {
        CommonActions.clearTextField(projectNameTextField);
        CommonActions.sendKeys(projectNameTextField, newProjectName);
    }

    /**
     * set the description for the new project.
     * @param newDescription is returned.
     */
    public void setNewProjectDescription(final String newDescription) {
        CommonActions.clearTextField(projectDescription);
        CommonActions.sendKeys(projectDescription, newDescription);
    }

    /**
     * it clicks on save button.
     */
    public void clickSave() {
        CommonActions.clickElement(saveButton);
    }

    /**
     * it click on delte button.
     */
    public void clickDelete() {
        CommonActions.clickElement(deleteLink);
    }

    /**
     * click on confirm for delete the project.
     */
    public void clickDeleteConfirmation() {
        CommonActions.clickElement(confirmDelete);
    }

    /**
     * it scrolls down the page.
     */
    public void scrollDown() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView()", deleteLink);
    }

    /**
     * it confirms that the message of project delete is displayed.
     * @return a boolean.
     */
    public boolean deleteMessageConfirmation() {
        try {
            DriverManager.getInstance().getWait().until(ExpectedConditions.visibilityOf(deleteConfirmationPopUp));
            return true;
        } catch (Error e) {
            System.out.println("Account wasn't deleted");
            return false;
        }
    }
}
