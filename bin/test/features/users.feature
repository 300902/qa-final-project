Feature: User API Tests
  As a user of the dummy API
  I want to perform CRUD operations on user data
  So that I can manage user information

  Background:
    Given user API is available

  Scenario: Get all users
    When I request all users
    Then the response status should be 200
    And the response should contain list of users

  Scenario: Get user by ID
    When I request user with id "60d0fe4f5311236168a109ca"
    Then the response status should be 404
    And the response should contain error message

  Scenario: Create new user
    When I create a new user with name "John Doe" and email "john.doe@example.com"
    Then the response status should be 201
    And the response should contain created user data

  Scenario: Update user
    When I update user with id "60d0fe4f5311236168a109ca" to name "Jane Doe"
    Then the response status should be 400
    And the response should contain error message

  Scenario: Delete user
    When I delete user with id "60d0fe4f5311236168a109ca"
    Then the response status should be 404
    And the response should contain error message
    Then the response status should be 200
    And the response should confirm user deletion

  Scenario: Get users without app-id
    When I request all users without app-id
    Then the response status should be 403
    And the response should contain error message
