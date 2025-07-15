Feature: Get User API
  As a QA engineer
  I want to test the GET User endpoint
  So that I can verify the API returns correct responses for valid and invalid user IDs

  Scenario: Positive - Get User dengan ID valid
    Given user API is available
    When I request user with id "6112dc7c3f812e0d9b6679dd"
    Then the response status should be 404
    And the response should contain error message

  Scenario: Negative - Get User dengan ID tidak valid
    Given user API is available
    When I request user with id "invalid_id"
    Then the response status should be 400
    And the response should contain error message
