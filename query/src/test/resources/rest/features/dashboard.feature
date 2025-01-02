
Feature: Tests the DashBoard Query Service using a REST client.

  Scenario: Tests out pagination capability
    When I POST a REST request to URL "/q/getBusesWithAttendance" with payload
"""
{
    "filters": {
        "busStopName1": "Main Street Station",
        "busStopName2": "West End Stop"
    },
    "pageNum": 1,
    "numRowsInPage": 15
}
"""
    Then the http status code is 200
    And the top level code is 200
    And success is true
    And the REST response key "numRowsReturned" is "1"
    And the REST response key "currentPage" is "1"
    And the REST response key "maxPages" is "1"

  Scenario: Test Likes query
    When I POST a REST request to URL "/q/getRouteBusStopMapper" with payload
"""
{
	"filters" :{
		"routeId": "1"
	}
}
"""
    Then the http status code is 200
    And the top level code is 200
    And success is true
    And the REST response key "numRowsReturned" is "8"

  Scenario: Test Likes query
    When I POST a REST request to URL "/q/getRouteBusStopMapper" with payload
"""
{
	"filters" :{
		"routeId": "2"
	}
}
"""
    Then the http status code is 200
    And the top level code is 200
    And success is true
    And the REST response key "numRowsReturned" is "1"
