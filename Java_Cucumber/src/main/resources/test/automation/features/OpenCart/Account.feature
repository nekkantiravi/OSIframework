Feature: Customer can create and edit account

  @OpenCart @Database
  Scenario: Customer can create account
    Given I visit "OpenCart Home" page
    And I navigate to create account page
    When I submit registration form with valid details
    Then my open cart account should be created

  @OpenCart @Database @WebService
  Scenario: Customer can edit account
    Given I visit OpenCart as a registered user
    And I navigate to edit account page
    When I update my account details
    Then my open cart account should be updated