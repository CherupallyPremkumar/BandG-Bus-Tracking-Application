Feature: Tests the Chenile Workflow Service using a REST client. Userservice service exists and is under test.
It helps to create a userservice and manages the state of the userservice as follows:
OPENED -(assign) -> ASSIGNED -(resolve) -> RESOLVED -(close) -> CLOSED
 
  Scenario: Test create userservice
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I construct a REST request with header "x-chenile-tenant-id" and value "tenant0"
    When I POST a REST request to URL "/userservice" with payload
    """
    {
	    "openedBy": "USER1",
	    "description": "Description"
	}
	"""
    Then the REST response contains key "mutatedEntity"
    And store "$.payload.mutatedEntity.id" from  response to "id"
    And the REST response key "mutatedEntity.currentState.stateId" is "OPENED"
    And the REST response key "mutatedEntity.openedBy" is "USER1"
    And the REST response key "mutatedEntity.description" is "Description"
	  
	Scenario: Retrieve the userservice that just got created
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I construct a REST request with header "x-chenile-tenant-id" and value "tenant0"
	  When I GET a REST request to URL "/userservice/${id}" 
	  Then the REST response contains key "mutatedEntity"
	  And the REST response key "mutatedEntity.id" is "${id}"
	  And the REST response key "mutatedEntity.currentState.stateId" is "OPENED"
	  And the REST response key "mutatedEntity.openedBy" is "USER1"
	  
	Scenario: Assign the userservice to an assignee with comments
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I construct a REST request with header "x-chenile-tenant-id" and value "tenant0"
		When I PATCH a REST request to URL "/userservice/${id}/assign" with payload
		"""
		{
			"assignee": "MY-ASSIGNEE",
			"comment": "MY-ASSIGNEE-CAN-FIX-THIS"
		}
		"""
	  Then the REST response contains key "mutatedEntity"
	  And the REST response key "mutatedEntity.id" is "${id}"
	  And the REST response key "mutatedEntity.currentState.stateId" is "ASSIGNED"
	  And the REST response key "mutatedEntity.assignee" is "MY-ASSIGNEE"
	  And the REST response key "mutatedEntity.assignComment" is "MY-ASSIGNEE-CAN-FIX-THIS"
	  
	 Scenario: Resolve the userservice with comments
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I construct a REST request with header "x-chenile-tenant-id" and value "tenant0"
		When I PATCH a REST request to URL "/userservice/${id}/resolve" with payload
		"""
		{
			"comment": "CANNOT-DUPLICATE"
		}
		"""
	  Then the REST response contains key "mutatedEntity"
	  And the REST response key "mutatedEntity.id" is "${id}"
	  And the REST response key "mutatedEntity.currentState.stateId" is "RESOLVED"
	  And the REST response key "mutatedEntity.resolveComment" is "CANNOT-DUPLICATE"
	  
	 Scenario: Close the userservice with comments
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I construct a REST request with header "x-chenile-tenant-id" and value "tenant0"
		When I PATCH a REST request to URL "/userservice/${id}/close" with payload
		"""
		{
			"comment": "OK"
		}
		"""
	  Then the REST response contains key "mutatedEntity"
	  And the REST response key "mutatedEntity.id" is "${id}"
	  And the REST response key "mutatedEntity.currentState.stateId" is "CLOSED"
	  And the REST response key "mutatedEntity.closeComment" is "OK" 
	  
	 Scenario: Assign a closed userservice to someone. This will err out.
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I construct a REST request with header "x-chenile-tenant-id" and value "tenant0"
		When I PATCH a REST request to URL "/userservice/${id}/assign" with payload
		"""
		{
			"assignee": "MY-ASSIGNEE",
			"comment": "MY-ASSIGNEE-CAN-FIX-THIS"
		}
		"""
		Then the REST response does not contain key "mutatedEntity"
		And the http status code is 422

	  