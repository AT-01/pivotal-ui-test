Feature: Project CRUD

  Background: Login
    When I perform a login as
    Then I will have a user logged

  Scenario: Verify that a new project is created
    When I added a new project
    Then Verify that a new project is created