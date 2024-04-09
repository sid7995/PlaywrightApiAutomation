Feature: API Tests

  @r1
  Scenario: Verify GET API functionality
    Given I have a valid API endpoint "/users"
    When I send a GET request
    Then I should receive a valid response

  @r1
  Scenario: Verify POST API functionality
    Given I have a valid API endpoint "/users"
    When I send a POST request
    Then verify status code
    #Then I should receive a valid response for post