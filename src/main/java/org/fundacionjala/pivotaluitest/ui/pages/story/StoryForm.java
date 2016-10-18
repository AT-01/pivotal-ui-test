package org.fundacionjala.pivotaluitest.ui.pages.story;

import org.fundacionjala.pivotaluitest.ui.pages.AbstractBasePage;
import org.fundacionjala.pivotaluitest.ui.pages.common.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

import static org.fundacionjala.pivotaluitest.ui.pages.story.StoriesSteps.*;

/**
 * Created by ErickaViraca on 10/12/2016.
 */
public class StoryForm extends AbstractBasePage {

    @FindBy(name = "story[name]")
    private WebElement storyTitleTextArea;
    @FindBy(css = ".dropdown.story_type")
    private WebElement storyTypeOption;
    @FindBy(css = "div[class='dropdown story_estimate']")
    private WebElement pointsOption;
    @FindBy(xpath = "//div[contains(@class,'markup')]")
    private WebElement descriptionField;
    @FindBy(xpath = "//textarea[@with-story-epic-links='true']")
    private WebElement descriptionTextAreaAble;
    @FindBy(name = "label[name]")
    private WebElement label;
    @FindBy(css = ".autosaves.label.name")
    private WebElement labelName;
    @FindBy(css = "button[class='autosaves std add']")
    private WebElement saveTask;
    @FindBy(xpath = "//button[contains(.,'Cancel')]")
    private WebElement cancelCreateStoryButton;
    @FindBy(css = "button[class='autosaves button std save']")
    private WebElement saveButton;
    @FindBy(xpath = "//button[@title='Delete this story']")
    private WebElement deleteStoryButton;
    @FindBy(xpath = "//button[@data-aid='DeleteButton']")
    private WebElement confirmDeleteButton;
    @FindBy(xpath = "//button[contains(.,'Done')]")
    private WebElement doneDescriptionButton;
    @FindBy(css = "div[class='story_owners']")
    private WebElement addOwnersButton;
    @FindBy(xpath = "//button[contains(.,'Close')]")
    private WebElement closeButton;
    @FindBy(xpath = "//a[@class='expander undraggable']")
    private WebElement storyExpander;
    @FindBy(xpath = "//a[@class='autosaves collapser']")
    private WebElement storyCollapser;

    private String storyTypeName;
    private String numPoint;

    /**
     * Sets the story title field.
     *
     * @param storyName, the name of the story.
     */
    public final void setStoryTitleField(String storyName) {
        CommonActions.clearTextField(storyTitleTextArea);
        CommonActions.sendKeys(storyTitleTextArea, storyName);
    }

    /**
     * Selects a type for a story.
     *
     * @param storyType, the story that must be selected.
     */
    public final void selectStoryType(String storyType) {
        storyTypeName = storyType.toLowerCase();
        CommonActions.clickElement(storyTypeOption);
        CommonActions.clickElement(driver.findElement(By.cssSelector("a[class='item_" + storyTypeName + " ']")));
    }

    /**
     * Selects the points for a story.
     *
     * @param points, the points that must be selected.
     */
    public final void selectPointsOption(String points) {
        CommonActions.clickElement(pointsOption);
        numPoint = String.valueOf(points.charAt(0));
        CommonActions.clickElement(driver.findElement(By.cssSelector("a[class='item_" + numPoint + " ']")));
    }

    /**
     * Sets the description field of a story.
     *
     * @param description, the text to be inserted on the description story field.
     */
    public final void setDescriptionField(String description) {
        CommonActions.clickElement(descriptionField);
        CommonActions.sendKeys(descriptionTextAreaAble, description);
        doneDescriptionButton.click();
    }

    /**
     * Sets the label field of a story.
     *
     * @param labelField, the text to be inserted on the label story field.
     */
    public final void setLabelsField(String labelField) {
        CommonActions.clickElement(label);
        CommonActions.sendKeys(label, labelField);
        CommonActions.sendAndPressKey(label, Keys.ENTER);
    }

    /**
     * Method that clicks on save story button.
     */
    public void clickSaveStory() {
        CommonActions.clickElement(saveButton);
    }

    /**
     * Methos that clicks on delete story button.
     */
    public void clickOnDeleteStoryButton() {
        CommonActions.clickElement(deleteStoryButton);
    }

    /**
     * General method to set the values of properties of a story
     *
     * @param values Map of properties to set of a story
     */
    public void strategyStepMap(final Map<StoriesSteps, Object> values) {
        Map<StoriesSteps, IAutomationStep> strategyMap = new HashMap<>();
        strategyMap.put(Story_name, () -> setStoryTitleField(String.valueOf(values.get(Story_name))));
        strategyMap.put(Story_type, () -> selectStoryType(String.valueOf(values.get(Story_type))));
        strategyMap.put(Points, () -> selectPointsOption(String.valueOf(values.get(Points)).toLowerCase()));
        strategyMap.put(Description, () -> setDescriptionField(String.valueOf(values.get(Description))));
        for (StoriesSteps step : values.keySet()) {
            strategyMap.get(step).executeStep();
        }
    }

    /**
     * General method to compare the values of properties of a story
     *
     * @return map with the current values
     */
    public Map<StoriesSteps, Object> getAssertionMap() {
        Map<StoriesSteps, Object> assertionMap = new HashMap<>();
        assertionMap.put(Story_name, getStoryTitle());
        assertionMap.put(Story_type, getStoryType());
        assertionMap.put(Points, getPoints());
        assertionMap.put(Description, getDescriptionText());
        return assertionMap;
    }

    /**
     * Clicks on option link to expand the Story information created.
     */
    public void clickOnExpanderStory() {
        CommonActions.clickElement(storyExpander);
    }

    /**
     * Clicks on option link to collapse the Story information created.
     */
    public void clickOnCollapserStory() {
        CommonActions.clickElement(storyCollapser);
    }

    /**
     * Clicks on confirm delete story button.
     */
    public void clickOnConfirmDeleteStoryButton() {
        CommonActions.clickElement(confirmDeleteButton);
        driver.navigate().refresh();
    }

    public String getStoryTitle() {
        return storyTitleTextArea.getText();
    }

    public String getStoryType() {
        return driver.findElement(By.cssSelector("a[class='selection item_" + storyTypeName.toLowerCase() + "']")).getText().toLowerCase();
    }

    public String getPoints() {
        return driver.findElement(By.cssSelector("a[class='selection item_" + numPoint + "'] > span")).getText().toLowerCase();
    }

    public String getDescriptionText() {
        return descriptionField.getText();
    }

}
