@all
Feature: Verify end points working as expected
  Scenario:
    Given Shuffle the Cards
    When Draw a Card
    Then Verify Cards Dealt