package org.fundacionjala.pivotal.ui.pages.project;

import org.fundacionjala.pivotal.ui.pages.AbstractBasePage;
import org.fundacionjala.pivotal.ui.pages.common.CommonActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fundacionjala.pivotal.ui.browser.DriverManager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.fundacionjala.pivotal.ui.pages.common.CommonActions.clickElement;

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
     * Thits method will select setting tab.
     *
     * @return SettingsProject.
     */
    public SettingsProject clickSettingsTab() {
        clickElement(navBarSettings);
        return new SettingsProject();
    }

    /**
     * This method will select Story navtab.
     *
     * @return Story.
     */
    public Story clickStoryTab() {

        clickElement(staorytab);
        return new Story();

    }


    /**
     * This method will update the projectTitle.
     *
     * @param name to change.
     */
    public void ediProjectNAme(final String name) {
        CommonActions.clickElement(projectName);
        CommonActions.sendKeys(projectName, name);
    }

    /**
     * This method click save edit project.
     */
    public void saveEditProject() {
        CommonActions.clickElement(save);
    }

    /**
     * go to setting for the project.
     * @param accountToSet String parameter
     */
    public void goToSettings(final String accountToSet) {
        driver.navigate().to("https://www.pivotaltracker.com/projects/" + accountToSet + "/settings");
    }

    /**
     * set a new project name.
     * @param newProjectName String parameter
     */
    public void setNewProjectName(final String newProjectName) {
        CommonActions.clearTextField(projectNameTextField);
        CommonActions.sendKeys(projectNameTextField, newProjectName);
    }

    /**
     * set a new description for the project.
     * @param newDescription String parameter
     */
    public void setNewProjectDescription(final String newDescription) {
        CommonActions.clearTextField(projectDescription);
        CommonActions.sendKeys(projectDescription, newDescription);
    }

    /**
     * click on save the project.
     */
    public void clickSave() {
        CommonActions.clickElement(saveButton);
    }

    /**
     * click on delete the project.
     */
    public void clickDelete() {
        CommonActions.clickElement(deleteLink);
    }

    /**
     * click on confirm.
     */
    public void clickDeleteConfirmation() {
        CommonActions.clickElement(confirmDelete);
    }

    /**
     * scroll down the page.
     */
    public void scrollDown() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        jse.executeScript("arguments[0].scrollIntoView()", deleteLink);

    }

    /**
     * verify the confirmation that the project has been deleted.
     * @return a boolean
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
    /**
     * this project obtain project name in the settings page.
     *
     * @return project name.
     */
    public String getNewProjectName() {
        return CommonActions.getText(newProjectName);
    }

}
