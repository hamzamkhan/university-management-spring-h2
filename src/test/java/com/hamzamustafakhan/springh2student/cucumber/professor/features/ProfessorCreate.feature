Feature: Professor Create
  Scenario: Create a professor
    Given Open browser and launch app
    And Home page is displayed in browser
    Then User clicks on manage professors link
    Then User sees Manage Professors page
    When Click on Add Professor button
    And New form is displayed in browser
    When Enter details in the form of professor
    Then Click submit after entering details