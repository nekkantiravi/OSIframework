Feature: Responsive search page

  @Search @Responsive
  Scenario: Verify Google Results page in mobile device
    Given I visit "Responsive Search" page
    When I "search" with "selenium" on current page
    Then I should be on "Responsive Results" page
    And I should see "seleniumhq.org" in "results" list
