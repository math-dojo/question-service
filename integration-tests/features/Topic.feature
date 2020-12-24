Feature: Features related to Topic Management
   Background: Create pre-configured user
    Given I generate a json payload called 'newQuestion'
    And I make a POST to the function at '/questions'
    Given I generate a json payload called 'newTopic'
    And I make a POST to the function at '/topics'

  @createtopic
  Scenario: POST to /topics with valid body returns created topic
    Given I generate a json payload called 'newTopic'
    When I make a POST to the function at '/topics'
    Then I should get a status code 200
    And the response should be a superset of all the keys and values set from 'newTopic'

 @getTopicById
  Scenario: GET to /topics/{topicId} with valid body returns created topic
    When I make a GET to the function at '/topics/pure-mathematics'
    Then I should get a status code 200
    And the response should be a superset of all the keys and values set from 'newTopic'

@updateTopicById
  Scenario: PUT to /topics/{topicId} with valid body returns created topic
   Given I generate a json payload called 'newTopicUpdate'
    When I make a PUT to the function at '/topics/pure-mathematics'
    Then I should get a status code 200
    And the response should be a superset of all the keys and values set from 'newTopicUpdate'
@getQuestions
  Scenario: GET to /topics/{topicId}/questions with valid body returns created topic
    When I make a GET to the function at '/topics/pure-mathematics/questions'
    Then I should get a status code 200
    And the response should be a superset of all the keys and values set from 'newQuestion'
@deleteTopicById
  Scenario: DELETE to /topics with valid body returns created topic
    When I make a DELETE to the function at '/topics/try-me-first'
    Then I should get a status code 204