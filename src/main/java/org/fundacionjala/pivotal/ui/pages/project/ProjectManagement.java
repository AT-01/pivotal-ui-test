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

    public void goToSettings(final String accountToSet) {
        driver.navigate().to("https://www.pivotaltracker.com/projects/"+accountToSet+"/settings");
    }

    public void setNewProjectName(final String newProjectName) {
        CommonActions.clearTextField(projectNameTextField);
        CommonActions.sendKeys(projectNameTextField, newProjectName);
    }

    public void setNewProjectDescription(final String newDescription) {
        CommonActions.clearTextField(projectDescription);
        CommonActions.sendKeys(projectDescription, newDescription);
    }

    public void clickSave() {
        CommonActions.clickElement(saveButton);
    }

    public void clickDelete(){
        CommonActions.clickElement(deleteLink);
    }

    public void clickDeleteConfirmation(){
        CommonActions.clickElement(confirmDelete);
    }

    public void scrollDown(){
        JavascriptExecutor jse = (JavascriptExecutor)driver;

        jse.executeScript("arguments[0].scrollIntoView()", deleteLink);

    }

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
