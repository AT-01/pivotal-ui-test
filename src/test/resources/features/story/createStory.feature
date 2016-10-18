Feature: CRUD - Stories Pivotal tracker  - CR

  Background: create a project (environment)
    When  I send a POST request to /projects
      | name          | AT01-Project |
      | public        | true         |
    Then I expect the status code 200
    And I store as Project1

  @deleteAllProjects
  Scenario: Verify all the story information is the correct
    Given I enter to [Project1.name]
    When I create a new story
      | Story_name          | AT01-Story      |
      | Story_type          | feature         |
      | Points              | 1 point         |
      | Description         | descriptiontest |
    Then Verify all the story information was successfully created
