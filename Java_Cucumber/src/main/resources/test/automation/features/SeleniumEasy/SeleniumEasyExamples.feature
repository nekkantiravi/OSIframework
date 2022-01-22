Feature: Selenium easy examples

  @SeleniumEasy
  Scenario: Fill selenium easy form
    Given I visit "SeleniumEasy Home" page
    And I "selectMenu" with "Input Forms, Input Form Submit" on "navigationBar" panel
    When I fill "input" form on "SeleniumEasy InputForm" page with following:
      | first_name | My First Name |
      | last_name  | My Last Name  |
    And I select random value in "state" dropdown
    Then I should see "sendButton" is enabled

  @SeleniumEasy
  Scenario: Fill selenium easy input form
    Given I visit "SeleniumEasy Home" page
    When I "selectMenu" with "Input Forms, Input Form Submit" on "navigationBar" panel
    Then I should be on "SeleniumEasy InputForm" page
    When I fill the selenium easy input form
    Then I should see "sendButton" is enabled

  @SeleniumEasy @Dynamic
  Scenario: Loading the data Dynamically
    Given I visit "SeleniumEasy Home" page
    And I "selectMenu" with "Others, Dynamic Data Loading" on "navigationBar" panel
    When I click on "getNewUser" button on "SeleniumEasy DynamicData" page
    Then I should see user image is loaded
