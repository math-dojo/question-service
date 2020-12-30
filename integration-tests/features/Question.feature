Feature: Features related to Question Management

 @deleteQuestionById @errorHandling
  Scenario: DELETE to /questions with valid body returns created question
    When I make a DELETE to the function at '/questions/doesnt-exist'
    Then I should get a status code 404    

