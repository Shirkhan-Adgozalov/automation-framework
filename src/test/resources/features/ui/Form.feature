Feature: DemoQA Practice Form

  Scenario: Submit student registration form successfully
    Given I open DemoQA practice form page
    When I enter first name "John"
    And I enter last name "Doe"
    And I enter email "john.doe@test.com"
    And I select gender Male
    And I enter mobile number "1234567890"
    And I submit the form
    Then form should be submitted successfully