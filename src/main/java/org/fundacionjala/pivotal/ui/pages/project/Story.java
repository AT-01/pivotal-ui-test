package org.fundacionjala.pivotal.ui.pages.project;

import org.fundacionjala.pivotal.ui.pages.AbstractBasePage;
import org.fundacionjala.pivotal.ui.pages.common.CommonActions;
import org.fundacionjala.pivotal.ui.pages.common.UICommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static org.fundacionjala.pivotal.ui.pages.common.CommonActions.clickElement;

/**
 * Created by Bruno Barrios on 4/12/2017.
 */
public class Story extends AbstractBasePage {
    @FindBy(css = ".add_story span")
    private WebElement addStroyBtn;

    @FindBy(name = "story[name]")
    private WebElement storyname;

    @FindBy(css = ".autosaves.button.std.save")
    private WebElement saveStory;

    @FindBy(css = "textarea[data-aid='new']")
    private WebElement taskTextfield;

    @FindBy(css = "button[data-aid='addTaskButton']")
    private WebElement addTaskButton;

    @FindBy(css = "span[data-aid='delete']")
    private WebElement deleteTaskButton;

    /**
     * This method will click add story button.
     */
    public void clickAddStoryButton() {
        clickElement(addStroyBtn);
    }

    /**
     * This method will set story title.
     *
     * @param storyTitle to be set.
     */
    public void setStoryTitle(String storyTitle) {
        CommonActions.clickElement(storyname);
        CommonActions.sendKeys(storyname, storyTitle);
    }

    /**
     * This method will click on save button.
     */
    public void clickSaveStory() {
        CommonActions.clickElement(saveStory);
    }

    /**
     * This method add a new task.
     *
     * @param taskName task name
     */
    public void addTask(String taskName) {
        CommonActions.setTextField(taskTextfield, taskName);
        clickOnAddTaskButton();
    }

    /**
     * This method click on add task button.
     */
    private void clickOnAddTaskButton() {
        CommonActions.clickElement(addTaskButton);
    }

    /**
     * This method verify if the task was created.
     *
     * @param taskName task name
     * @return true if the task was created, otherwise false
     */
    public boolean isTaskNamePresent(String taskName) {
        By taskNameBy = By.xpath("//div[@data-aid='TaskDescription' and contains(., '" + taskName + "')]");

        return UICommonMethods.isElementPresent(taskNameBy);
    }

    /**
     * This method perform the action to delete a task.
     *
     * @param taskName task name
     */
    public void deleteTask(String taskName)  {
        WebElement task = driver.findElement(By
                .xpath("//div[@data-aid='TaskDescription' and contains(., '" + taskName + "')]"));

        Actions action = new Actions(driver);
        action.moveToElement(task)
                .moveToElement(deleteTaskButton)
                .click()
                .build()
                .perform();
    }

    /**
     * This method verified if task was deleted.
     *
     * @param taskName true if the task was deleted, otherwise false
     * @return true if element is present, otherwise false
     */
    public boolean isTaskDeleted(String taskName) {
        By taskNameBy = By.xpath("//div[@data-aid='TaskDescription' and contains(., '" + taskName + "')]");

        return UICommonMethods.isElementPresent(taskNameBy);
    }
}
