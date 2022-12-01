Feature: Managing Students
  Scenario: Whole Student flow
    Given Open browser and launch application
    And Home page is displayed
    When User clicks on Manage Students link
    Then User sees Manage Students page
    When Click on Add student button
    And New form is displayed
    When Enter details in the form
    Then Click submit
    When Clicks on edit for created student
    And Student details displayed
    Then Changes date of birth
    And Click submit after edit
    Then Clicks on delete for the student
    And Check if the student still exists



