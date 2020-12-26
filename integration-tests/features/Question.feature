Feature: Features related to Question Management
  Background: Create pre-configured user
    Given I generate a random id json payload called 'newQuestion'
    And I make a POST to the function at '/questions'
    Given I generate a json payload called 'newQuestion'
    And I make a POST to the function at '/questions'
    
  @createQuestion @errorHandling
  Scenario: POST to /questions with valid body returns created question
    Given I generate a json payload called 'newQuestion'
    When I make a POST to the function at '/questions'
    Then I should get a status code 400

 @getQuestionById
  Scenario: GET to /questions/{questionId} with valid body returns created question
    When I make a GET to the function at '/questions/try-me-first'
    Then I should get a status code 200
    And the response should be a superset of all the keys and values set from 'newQuestion'
    
 @getQuestionById@errorHandling
  Scenario: GET to /questions/{questionId} with valid body returns created question
    When I make a GET to the function at '/questions/doesnt-exist'
    Then I should get a status code 404 

 @updateQuestionById
  Scenario: PUT to /questions/{questionId} with valid body returns created question
   Given I generate a json payload called 'newQuestionUpdate'
    When I make a PUT to the function at '/questions/try-me-first'
    Then I should get a status code 202  
    And the response should be a superset of all the keys and values set from 'newQuestionUpdate'
    
  @updateQuestionById @errorHandling
  Scenario: PUT to /questions/{questionId} with valid body returns created question
   Given I generate a json payload called 'newQuestionUpdate'
    When I make a PUT to the function at '/questions/wrong-question'
    Then I should get a status code 400

  @deleteQuestionById
  Scenario: DELETE to /questions with valid body returns created question
    When I make a DELETE to the function at '/questions/try-me-first'
    Then I should get a status code 204
  
  @deleteQuestionById @errorHandling
  Scenario: DELETE to /questions with valid body returns created question
    When I make a DELETE to the function at '/questions/doesnt-exist'
    Then I should get a status code 404    
       
