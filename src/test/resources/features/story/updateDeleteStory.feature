Feature: CRUD - Stories Pivotal tracker -UD

  Background: create a project and a story (environment)
    When  I send a POST request to /projects
      | name          | AT01-Project |
      | public        | true         |
    And I store as Project1
    And I send a POST request to /projects/[Project1.id]/stories
      | name          | AT01-Story   |
    And I store as Story1
    Then I expect the status code 200

  @deleteAllProjects
  Scenario: Verify that all the story fields was successfully edited
    Given I enter to [Project1.name]
    When I update the story name [AT01-Story-edited]
    Then Verify that the story name field was successfully edited [AT01-Story-edited]

  @deleteAllProjects
  Scenario: Verify that a new story is successfully deleted
    Given I enter to [Project1.name]
    When I delete the story
    Then Verify that the story [Story1.name] deleted is not displayed on the project