Feature: Features related to Topic Management
   Background: Create pre-configured user
    Given I generate a json payload called 'newQuestion'
    And I make a POST to the function at '/questions'
    Given I generate a json payload called 'newTopic'
    And I make a POST to the function at '/topics'
    Given I generate a random id json payload called 'newTopic'
    And I make a POST to the function at '/topics'

 
 @deleteTopicById@errorHandling
  Scenario: DELETE to /topics with valid body is completed
    When I make a DELETE to the function at '/topics/doesnt-exist'
    Then I should get a status code 404
 @getTopicById
  Scenario: GET to /topics/{topicId} with valid body returns created topic
    When I make a GET to the function at '/topics/pure-mathematics'
    Then I should get a status code 200
    And the response should be a superset of all the keys and values set from 'newTopic'
    
 @getTopicById @errorHandling
  Scenario: GET to /topics/{topicId} with valid body returns created topic
    When I make a GET to the function at '/topics/doesnt-exist'
    Then I should get a status code 404 
@createtopic @errorHandling
  Scenario: POST to /topics with valid body returns created topic
    Given I generate a json payload called 'newTopic'
    When I make a POST to the function at '/topics'
    Then I should get a status code 400

@updateTopicById
  Scenario: PUT to /topics/{topicId} with valid body returns created topic
   Given I generate a json payload called 'newTopicUpdate'
    When I make a PUT to the function at '/topics/pure-mathematics'
    Then I should get a status code 202
    And the response should be a superset of all the keys and values set from 'newTopicUpdate'
@getQuestions
  Scenario: GET to /topics/{topicId}/questions with valid body returns created topic
    When I make a GET to the function at '/topics/pure-mathematics/questions'
    Then I should get a status code 200
    And the response should be a superset of all the keys and values set from 'newQuestion'
@getQuestions @errorHandling
  Scenario: GET to /topics/{topicId}/questions with valid body returns created topic
    When I make a GET to the function at '/topics/doesnt-exist/questions'
    Then I should get a status code 404 
    
@deleteTopicById
  Scenario: DELETE to /topics with valid body is completed
    When I make a DELETE to the function at '/topics/pure-mathematics'
    Then I should get a status code 204
    
@getTopics
  Scenario: GET to /topics  returns all topics
    When I make a GET to the function at '/topics'
    Then I should get a status code 200     