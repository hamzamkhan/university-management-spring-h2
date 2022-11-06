Feature: Managing Students
  Scenario: Whole Student flow
    Given Open browser and launch application
    And Home page is displayed
    When User clicks on Manage Students link
    Then User sees Manage Students page
    Then Click on Add student button
    And New form is displayed
    When Enter details in the form
    Then Click submit

