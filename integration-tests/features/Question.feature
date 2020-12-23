Feature: Features related to Question Management
 
  @createUserInOrg
  Scenario: POST to /users with valid body returns created question
    Given I generate a json payload called 'newQuestion'
    When I make a POST to the function at '/questions'
    Then I should get a status code 200
    And the response should be a superset of all the keys and values set from 'newQuestion'
       
