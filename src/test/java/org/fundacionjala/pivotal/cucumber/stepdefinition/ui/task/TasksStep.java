package org.fundacionjala.pivotal.cucumber.stepdefinition.ui.task;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.fundacionjala.pivotal.api.Mapper;
import org.fundacionjala.pivotal.ui.pages.Dashboard;
import org.fundacionjala.pivotal.ui.pages.StoryDashBoard;


/**
 * Steps Definitions for the Task Feature.
 */
public class TasksStep {

    /**
     * Go to the project DashBoard and expand story.
     *
     * @param projectName String of the name of the project.
     * @param storyName   String of the name of the story.
     */
    @Given("^I select the project (.*) and story (.*) created$")
    public void iGoToTheProjectNameAndStoryNameCreated(final String projectName, final String storyName) {
        Dashboard dashboard = new Dashboard();
        String project = Mapper.mapEndpoint(projectName);
        String story = Mapper.mapEndpoint(storyName);
        StoryDashBoard projectDashboard = dashboard.projectsList(project);
        projectDashboard.clickForStoryDeploy(story);
    }

    /**
     * this method create a task.
     *
     * @param taskText String whit the new task.
     */
    @When("^I create a task whit the text: (.*)$")
    public void iCreateATaskWhitTheTextThisIsATaskTest(final String taskText) {
        StoryDashBoard projectDashboard = new StoryDashBoard();
        projectDashboard.setTaskText(taskText);
    }

    /**
     * this method update the task.
     *
     * @param taskCreated   String the old task.
     * @param textForUpdate String whit the new task.
     */
    @When("^I update the task (.*) for: (.*)$")
    public void iUpdateTheTaskForTaskUpdated(final String taskCreated, final String textForUpdate) {
        String oldTaskText = Mapper.mapEndpoint(taskCreated);
        StoryDashBoard storyDashBoard = new StoryDashBoard();
        storyDashBoard.updateTheTasks(oldTaskText, textForUpdate);
    }

    /**
     * Delete the task.
     *
     * @param taskCreated String whit the task tah will be deleted.
     */
    @When("^I delete the task: (.*)$")
    public void iDeleteTheTaskDescription(final String taskCreated) {
        StoryDashBoard storyDashBoard = new StoryDashBoard();
        storyDashBoard.deleteTask(taskCreated);
    }

}
