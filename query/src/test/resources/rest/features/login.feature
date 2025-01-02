Feature: Tests the Login Query Service using a REST client.

  Scenario: Tests out pagination capability
    When I POST a REST request to URL "/q/login" with payload
"""
{
    "filters": {
        "email": "john.doe@school.com",
        "password": "password123"
    }
}
"""
    Then the http status code is 200
    And the top level code is 200
    And success is true
    And the REST response key "numRowsReturned" is "1"
