package org.fundacionjala.pivotaluitest.cucumber.stepdefinition.ui.story;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.fundacionjala.pivotaluitest.ui.pages.Dashboard;
import org.fundacionjala.pivotaluitest.ui.pages.common.CommonNavigator;
import org.fundacionjala.pivotaluitest.ui.pages.project.ProjectSideBarMenu;
import org.fundacionjala.pivotaluitest.ui.pages.story.StoriesSteps;
import org.fundacionjala.pivotaluitest.ui.pages.story.StoryForm;

import java.util.Map;

import static org.fundacionjala.pivotaluitest.api.Mapper.mapEndpoint;

/**
 * Created by ErickaViraca on 9/23/2016.
 */
public class StorySteps {
    private ProjectSideBarMenu projectSideBarMenu;
    private StoryForm storyForm;
    private Map<StoriesSteps, Object> storiesValues;
    public StorySteps() {
        storyForm = new StoryForm();
    }

    /**
     * Enters to a specific project previously created.
     *
     * @param projectName the name of the project
     */
    @Given("^I enter to (.*)$")
    public void iAccessToProjectName(String projectName) {
        String name = mapEndpoint(projectName);
        CommonNavigator.goToDashboard();
        projectSideBarMenu = new Dashboard().clickOnProject(name);
    }

    /**
     * Creates a new story.
     *
     * @param values, a map of the values to create the new story.
     */
    @When("^I create a new story$")
    public void iCreateANewStoryOnProject(Map<StoriesSteps, Object> values) {
        this.storiesValues = values;
        storyForm = projectSideBarMenu.clickAddStory();
        storyForm.strategyStepMap(values);
        storyForm.clickSaveStory();
    }

    /**
     * Deletes a story.
     */
    @When("^I delete the story$")
    public void iDeleteTheStory() {
        storyForm.clickOnExpanderStory();
        storyForm.clickOnDeleteStoryButton();
        storyForm.clickOnConfirmDeleteStoryButton();
    }

    /**
     * Updates a story created.
     *
     * @param newStoryName the new story name to be updated.
     */
    @When("^I update the story name (.*)$")
    public void iUpdateTheStory(String newStoryName) {
        storyForm.clickOnExpanderStory();
        storyForm.setStoryTitleField(newStoryName);
        storyForm.clickSaveStory();
    }

    /**
     * Returns the storyForm with all the changes maked on this instance.
     *
     * @return the last storyForm.
     */
    public StoryForm getStory() {
        return storyForm;
    }

    /**
     * Returns the map of the story values of a specific story.
     *
     * @return the last story values map.
     */
    public Map<StoriesSteps, Object> getStoriesValues() {
        return storiesValues;
    }

}

