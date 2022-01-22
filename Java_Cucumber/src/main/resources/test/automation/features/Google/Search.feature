Feature: Search feature

  @Search @Screenshot @KeepBrowser
  Scenario: Verify Google Search page
    Given I visit "Google Search" page
    Then I should see following elements:
      | searchBox    |
      | searchButton |
    And I should see "footer" panel

  @Search @KeepBrowser
  Scenario: Verify Google Results page
    Given I visit "Google Search" page
    When I "search" with "selenium" on current page
    Then I should be on "Google Results" page
    And I should see following in "results" list:
      | cucumber.io    |
      | seleniumhq.org |

  @Search @KeepBrowser
  Scenario Outline: Verify search results for <keyword>
    Given I visit "Google Search" page
    When I "search" with "<keyword>" on current page
    Then I should see "<website>" in "results" list on "Google Results" page

    Examples:
      | keyword  | website        |
      | selenium | seleniumhq.org |
      | cucumber | cucumber.io    |
