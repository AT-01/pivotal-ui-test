package org.fundacionjala.pivotaluitest.cucumber.stepdefinition.ui.story;

import cucumber.api.java.en.Then;
import org.fundacionjala.pivotaluitest.ui.pages.story.StoriesSteps;
import org.fundacionjala.pivotaluitest.ui.pages.story.StoryForm;
import org.fundacionjala.pivotaluitest.ui.pages.story.StorySideBarMenu;
import org.fundacionjala.pivotaluitest.ui.pages.workspace.WorkspaceSteps;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by ErickaViraca on 10/12/2016.
 */
public class StoryAsserts {

    private StorySteps storySteps;
    StoryForm story = new StoryForm();
    StorySideBarMenu storySideBarMenu = new StorySideBarMenu();

    private Map<WorkspaceSteps, Object> storiesValues;


    public StoryAsserts(StorySteps storySteps) {
        this.storySteps = storySteps;
    }

    /**
     *Verifys if all the story information is created correctly.
     */
    @Then("^Verify all the story information was successfully created$")
    public void verifyAllTheStoryInformationWasSuccessfullyCreated() {
        Map<StoriesSteps, Object> storiesValues = storySteps.getStoriesValues();
        story = storySteps.getStory();
        story.clickOnExpanderStory();
        storiesValues.keySet().forEach(step -> {
            assertEquals(story.getAssertionMap().get(step), storiesValues.get(step));
        });
        story.clickOnCollapserStory();
    }

    /**
     * This method delete the story.
     *
     * @param storyNameDeleted String whit the task that will be deleted.
     */
    @Then("^Verify that the story (.*) deleted is not displayed on the project$")
    public void verifyThatTheStoryDeletedIsNotDisplayedOnTheProject(final String storyNameDeleted) {
        assertFalse(storySideBarMenu.storyExist(storyNameDeleted));
    }

    /**
     * Verifies if the name story field is updated succesfully.
     *
     * @param nameStoryExpected, the name that must be.
     */
    @Then("^Verify that the story name field was successfully edited (.*)$")
    public void verifyThatTheStoryNameFieldWasSuccessfullyEdited(String nameStoryExpected) {
        assertEquals(nameStoryExpected, storySteps.getStoriesValues().get("Story_name"));
    }
}

