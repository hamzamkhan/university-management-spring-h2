Feature: Professor Update And Delete
  Scenario: Update Professor
    When Clicks on edit for created professor
    And Professor details displayed
    Then Changes date of birth of professor
    Then Click submit after editing details

  Scenario: Delete Professor
      When Click on delete professor
      Then Professor gets deleted