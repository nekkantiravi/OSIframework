Feature: Report demo fail and auto rerun

  @ReportDemo @FlakyTest
  Scenario: Flaky Test I will fail for the first time
    Given I visit "SeleniumEasy Home" page
    When I "selectMenu" with "Progress Bars, Bootstrap Progress bar" on "navigationBar" panel
    Then I will fail for the first time

  @ReportDemo @AlwaysFail
  Scenario: I will always fail for the report
    Given I visit "SeleniumEasy Home" page
    When I "selectMenu" with "List Box, Data List Filter" on "navigationBar" panel
    Then I will always fail
    And I will always fail

  @ReportDemo @Undefined
  Scenario: My steps are undefined for the report
    Given I visit some page
    When I do some action
    Then I should see some thing
