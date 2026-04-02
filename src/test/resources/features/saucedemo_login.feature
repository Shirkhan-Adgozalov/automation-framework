Feature: Saucedemo Login with Test Data
  As a user
  I want to login to Saucedemo using predefined test data
  So that I can test different user scenarios and validate login functionality

  Background:
    Given I am on the saucedemo login page
    And I have loaded test data from DataGenerator

  @saucedemo @smoke
  Scenario: Login with valid credentials from DataGenerator
    Given I want to use test case "TC001" from DataGenerator
    When I login with data from DataGenerator
    And I submit the login form
    Then I should be logged in successfully
    And I should see the products page

  @saucedemo @regression
  Scenario: Login with locked out user from DataGenerator
    Given I want to use test case "TC002" from DataGenerator
    When I login with data from DataGenerator
    And I submit the login form
    Then I should see an error message

  @saucedemo @data-driven
  Scenario: Login with random test case from DataGenerator
    Given I want to use a random test case from DataGenerator
    When I login with random data from DataGenerator
    And I submit the login form
    Then I should either be logged in successfully or see an error message

  @saucedemo @validation
  Scenario: Verify DataGenerator data is loaded correctly
    Given I load all test cases from DataGenerator
    Then I should see at least "6" test cases available
    And each test case should have valid data
    And data should contain username, password, first name, and last name

  @saucedemo @row-based
  Scenario: Login using row number from DataGenerator
    Given I want to use data from row "3" in DataGenerator
    When I login with data from specific row
    And I submit the login form
    Then the login should be successful
    And login fields should contain the correct data

  @saucedemo @multiple
  Scenario: Test multiple login scenarios using different test cases
    When I fill and submit login with test case "TC001"
    And I logout from the application
    And I fill and submit login with test case "TC003"
    Then both logins should be successful
    And each login should use different data from DataGenerator

  @saucedemo @negative
  Scenario: Login with invalid credentials from DataGenerator
    Given I want to use test case "TC002" from DataGenerator
    When I login with data from DataGenerator
    And I submit the login form
    Then I should see an error message
    And I should still be on the login page

  @saucedemo @performance
  Scenario: Login with performance glitch user from DataGenerator
    Given I want to use test case "TC004" from DataGenerator
    When I login with data from DataGenerator
    And I submit the login form
    Then I should be logged in successfully
    And I should see the products page

  @saucedemo @visual
  Scenario: Login with visual user from DataGenerator
    Given I want to use test case "TC006" from DataGenerator
    When I login with data from DataGenerator
    And I submit the login form
    Then I should be logged in successfully
    And I should see the products page
