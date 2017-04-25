package org.fundacionjala.pivotal.ui.pages;

import org.fundacionjala.pivotal.ui.pages.common.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * This class have the actions on the Story Board.
 */
public class StoryDashBoard extends AbstractBasePage {


    private static final String TASK_TEXT_AREA = "textarea[data-aid='editor']";
    private static final String SAVE_BUTTON = "button[data-aid='saveTaskButton']";
    private static final String DELETE_BUTTON = "span[data-aid='delete']";
    public static final String ALL_DELETE_BUTTONS = "//li[contains(@class,'task draggable droppable task')]";

    @FindBy(css = ".raw_context_name.public")
    private WebElement nameOfProject;

    @FindBy(css = "textarea[placeholder='Add a task']")
    private WebElement taskTextField;

    @FindBy(css = "button[data-aid='addTaskButton']")
    private WebElement addButton;

    @FindBy(css = "section[class=\"tasks full\"]")
    private WebElement taskList;

    @FindBy(css = "a[class=\"autosaves undraggable edit\"]")
    private WebElement editButton;

    @FindBy(css = " div[class=\"tasks_index\"]")
    private WebElement taskIndex;


    /**
     * Deploys the story board.
     *
     * @param storyName String name of the Story.
     */
    public void clickForStoryDeploy(final String storyName) {
        CommonActions.clickElement(driver.findElement(
                By.cssSelector("header[class=\"preview\"] >a[title=" + storyName + "] "
                        + "+ [class=\"expander undraggable")));
    }

    /**
     * Set the text for the task.
     *
     * @param taskText String description of the task.
     */
    public void setTaskText(final String taskText) {
        CommonActions.sendKeys(taskTextField, taskText);
        CommonActions.clickElement(addButton);
    }

    /**
     * @param taskText The task text.
     * @return The task text.
     */
    public WebElement getTaskElementByText(final String taskText) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//div[text()='%s']", taskText))));
    }

    /**
     * Verifies If the Task exist.
     *
     * @param taskText String of the task.
     * @return boolean  element exist
     */
    public boolean taskTextExist(final String taskText) {
        try {
            List<WebElement> taskCreated = taskList.findElements(
                    By.cssSelector("div.TaskShow__description___3R_4oT7G"));
            return taskCreated.stream().anyMatch(x -> x.getText().equals(taskText));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * this method  find the ald task and update whit the a new task.
     *
     * @param oldText      String old task created.
     * @param textToUpdate String new task to update.
     */
    public void updateTheTasks(final String oldText, final String textToUpdate) {
        List<WebElement> tasksElements = taskIndex
                .findElements(By.xpath("//li[contains(@class,'task draggable droppable task')]"));
        tasksElements.stream()
                .filter(webElement -> oldText.equals(webElement.getText()))
                .forEach(webElement -> updateTheTask(webElement, textToUpdate));
    }

    /**
     * this method is in charge to update the task.
     *
     * @param webElement   Web element whit in the task
     * @param textToUpdate String the new task to update.
     */
    public void updateTheTask(final WebElement webElement, final String textToUpdate) {
        CommonActions.clickElement(webElement);

        WebElement textArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(TASK_TEXT_AREA)));
        CommonActions.sendKeys(textArea, textToUpdate);

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SAVE_BUTTON)));
        CommonActions.clickElement(saveButton);
    }

    /**
     * @param taskName The task name
     */
    public void deleteTask(final String taskName) {
        By locator = By.xpath(String.format("//div[text()='%s']/parent::div/descendant::span", taskName));

        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(locator));
        CommonActions.clickElement(deleteButton);
    }
}
