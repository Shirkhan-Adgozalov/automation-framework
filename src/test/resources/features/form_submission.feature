Feature: Form Submission with Google Sheets Data
  As a user
  I want to fill and submit forms using data from Google Sheets
  So that I can test form functionality with multiple test cases

  Background:
    Given I am on the practice form page
    And I have loaded test data from Google Sheets

  @form @smoke
  Scenario: Fill form with specific test case from Google Sheets
    Given I want to use test case "TC001" from Google Sheets
    When I fill the form with data from Google Sheets
    And I submit the form
    Then the form should be submitted successfully
    And I should see the success message

  @form @regression
  Scenario: Fill form with another test case from Google Sheets
    Given I want to use test case "TC002" from Google Sheets
    When I fill the form with data from Google Sheets
    And I submit the form
    Then the form should be submitted successfully
    And the confirmation should display the submitted data

  @form @data-driven
  Scenario: Fill form with random test case from Google Sheets
    Given I want to use a random test case from Google Sheets
    When I fill the form with random data from Google Sheets
    And I submit the form
    Then the form should be submitted successfully
    And the submitted data should match the Google Sheets data

  @form @validation
  Scenario: Verify Google Sheets data is loaded correctly
    Given I load all test cases from Google Sheets
    Then I should see at least "2" test cases available
    And each test case should have valid data
    And the data should contain first name, last name, email, and mobile

  @form @row-based
  Scenario: Fill form using row number from Google Sheets
    Given I want to use data from row "2" in Google Sheets
    When I fill the form with data from specific row
    And I submit the form
    Then the form should be submitted successfully
    And the form fields should contain the correct data

  @form @multiple
  Scenario: Submit multiple forms using different test cases
    Given I have loaded test data from Google Sheets
    When I fill and submit form with test case "TC001"
    And I fill and submit form with test case "TC002"
    Then both forms should be submitted successfully
    And each form should use different data from Google Sheets
