package io.mathdojo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.microsoft.azure.functions.ExecutionContext;
@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringRunner.class)
public class QuestionHandlerTest {
	private ExecutionContext mockExecContext;
	private Question testQuestion1 = new Question("test-question-1", "test question 1", "test", "test", 50, "easy",  new String[]{""}, "test", new String[]{""}, "test");
	private Question testQuestion =  new Question("test-question", "test question", "test", "test", 50, "easy",  new String[]{""}, "test", new String[]{""}, "test");
	@Before
	 public void setUp() {
         Logger testLogger = mock(Logger.class);
         mockExecContext = mock(ExecutionContext.class);
         Mockito.when(mockExecContext.getLogger()).thenReturn(testLogger);

 }
	@Test
	public void testQuestionHandlerGetQuestion() {
		 when(mockExecContext.getFunctionName()).thenReturn("getQuestion");
		AzureSpringBootRequestHandler<Question, Question> handler = new AzureSpringBootRequestHandler<>(
				QuestionFunction.class);
		Question getQuestionResult = handler.handleRequest(testQuestion, mockExecContext);
		handler.close();
		assertEquals(getQuestionResult, testQuestion);

	}
	
	@Test
	public void testQuestionHandlerGetQuestionWhenEmpty() {
		 when(mockExecContext.getFunctionName()).thenReturn("getQuestion");
		AzureSpringBootRequestHandler<Question, Question> handler = new AzureSpringBootRequestHandler<>(
				QuestionFunction.class);
		Question getQuestionResult = handler.handleRequest(testQuestion1, mockExecContext);
		handler.close();
		assertEquals(getQuestionResult, Question.EMPTY_DATABASE);

	}
	
	@Test
	public void testQuestionHandlerCreateQuestion() {
		 when(mockExecContext.getFunctionName()).thenReturn("createQuestion");
		AzureSpringBootRequestHandler<Question, Question> handler = new AzureSpringBootRequestHandler<>(
				QuestionFunction.class);
		Question getQuestionResult = handler.handleRequest(testQuestion1, mockExecContext);
		handler.close();
		assertEquals(getQuestionResult, testQuestion1);


	}
	
	@Test
	public void testQuestionHandlerGetQuestionById() {
		 when(mockExecContext.getFunctionName()).thenReturn("getQuestionById");
		AzureSpringBootRequestHandler<Question, Question> handler = new AzureSpringBootRequestHandler<>(
				QuestionFunction.class);
		Question getQuestionResult = handler.handleRequest(testQuestion, mockExecContext);
		handler.close();
		assertEquals(getQuestionResult, testQuestion);

	}
	
	@Test
	public void testQuestionHandlerUpdateQuestion() {
		 when(mockExecContext.getFunctionName()).thenReturn("updateQuestion");
		AzureSpringBootRequestHandler<Question, Question> handler = new AzureSpringBootRequestHandler<>(
				QuestionFunction.class);
		Question newTestQuestion = new Question("test-question", "test question 1", "test", "test", 50, "hard",  new String[]{""}, "test", new String[]{""}, "test");
		Question getQuestionResult = handler.handleRequest(newTestQuestion, mockExecContext);
		handler.close();
		assertEquals(getQuestionResult, newTestQuestion);


	}
	
	@Test
	public void testQuestionHandlerDeleteQuestionThrowsNoErrorIfSuccessful() {
		 when(mockExecContext.getFunctionName()).thenReturn("deleteQuestion");
		AzureSpringBootRequestHandler<Question, Question> handler = new AzureSpringBootRequestHandler<>(
				QuestionFunction.class);
		handler.handleRequest(testQuestion, mockExecContext);
		 assertDoesNotThrow(() -> handler.handleRequest(testQuestion, mockExecContext));
		handler.close();
		

	}




}
