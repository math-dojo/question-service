Feature: Features related to Question Management
  Background: Create pre-configured user
    Given I generate a json payload called 'newQuestion'
    And I make a POST to the function at '/questions'
    
  @createQuestion
  Scenario: POST to /questions with valid body returns created question
    Given I generate a json payload called 'newQuestion'
    When I make a POST to the function at '/questions'
    Then I should get a status code 200
    And the response should be a superset of all the keys and values set from 'newQuestion'

 @getQuestionById
  Scenario: GET to /questions/{questionId} with valid body returns created question
    When I make a GET to the function at '/questions/try-me-first'
    Then I should get a status code 200
    And the response should be a superset of all the keys and values set from 'newQuestion'

@updateQuestionById
  Scenario: PUT to /questions/{questionId} with valid body returns created question
   Given I generate a json payload called 'newQuestionUpdate'
    When I make a PUT to the function at '/questions/try-me-first'
    Then I should get a status code 200
    And the response should be a superset of all the keys and values set from 'newQuestionUpdate'

@deleteQuestionById
  Scenario: DELETE to /questions with valid body returns created question
    When I make a DELETE to the function at '/questions/try-me-first'
    Then I should get a status code 204
       
