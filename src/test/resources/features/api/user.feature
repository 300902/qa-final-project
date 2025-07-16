Feature: Get User API
  As a QA engineer
  I want to test the GET User endpoint
  So that I can verify the API returns correct responses for valid and invalid user IDs

  @api
  Scenario: Positive - Get User dengan ID valid
    Given user API is available
    When I request user with id "6112dc7c3f812e0d9b6679dd"
    Then the response status should be 404
    And the response should contain error message

  @api
  Scenario: Negative - Get User dengan ID tidak valid
    Given user API is available
    When I request user with id "invalid_id"
    Then the response status should be 404
    And the response should contain error message

  @api
  Scenario: Positive - Get All Users
    Given user API is available
    When I request all users
    Then the response status should be 200
    And the response should contain list of users

  @api
  Scenario: Negative - Get All Users without app-id
    When I request all users without app-id
    Then the response status should be 403

  @api
  Scenario: Positive - Create User with valid payload
    Given user API is available
    When I create a new user with name "John" and email "john@mail.com"
    Then the response status should be 200
    And the response should contain created user data

  @api
  Scenario: Negative - Create User with invalid payload
    Given user API is available
    When I create a new user with name "" and email ""
    Then the response status should be 400

  @api
  Scenario: Positive - Update User with valid id and name
    Given user API is available
    When I update user with id "some_id" to name "NewName"
    Then the response status should be 200
    And the response should contain updated user data

  @api
  Scenario: Negative - Update User with invalid id and name
    Given user API is available
    When I update user with id "invalid_id" to name "Name"
    Then the response status should be 404

  @api
  Scenario: Positive - Delete User dengan ID valid
    Given user API is available
    When I delete user with id "some_id"
    Then the response status should be 200
    And the response should confirm user deletion

  @api
  Scenario: Negative - Delete User dengan ID invalid
    Given user API is available
    When I delete user with id "invalid_id"
    Then the response status should be 404
    And the response should contain error message
