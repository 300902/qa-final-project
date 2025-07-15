Feature: Login Demoblaze
  As a user
  I want to login to Demoblaze
  So that I can access my account

  @web
  Scenario: Successful login with valid credentials
    Given I am on the Demoblaze homepage
    When I click the Log in navigation
    And I enter username "validUser" and password "validPass"
    And I click the Log in button
    Then I should see the welcome message

  @web
  Scenario: Failed login with invalid credentials
    Given I am on the Demoblaze homepage
    When I click the Log in navigation
    And I enter username "invalidUser" and password "invalidPass"
    And I click the Log in button
    Then I should see an error message
