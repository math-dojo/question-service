package io.mathdojo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpRequestMessage;

import io.mathdojo.useraccountservice.security.HTTPRequestSignatureVerificationEnabledHandler;
import io.mathdojo.useraccountservice.security.SystemService;
@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringRunner.class)
@SuppressWarnings({"rawtypes","unchecked"})
@Ignore
public class QuestionHandlerTest {
	private ExecutionContext mockExecContext;
	private SystemService mockSystemService;
	private HttpRequestMessage mockMessage = mock(HttpRequestMessage.class);
	private Question testQuestion1 = new Question("test-question-1", "test question 1", "test", "test", 50, "easy",  new String[]{""}, "test", new String[]{""}, "test");
	@Before
	 public void setUp() {
         Logger testLogger = mock(Logger.class);
         mockExecContext = mock(ExecutionContext.class);
         Mockito.when(mockExecContext.getLogger()).thenReturn(testLogger);
         mockSystemService = mock(SystemService.class);
         when(mockSystemService.getFunctionEnv()).thenReturn("local");

 }
	@Test
	public void testQuestionHandlerGetQuestionByDifficulty() {
		 when(mockExecContext.getFunctionName()).thenReturn("getQuestionsByDifficulty");
		 HTTPRequestSignatureVerificationEnabledHandler<String, List<Question>> handler = new HTTPRequestSignatureVerificationEnabledHandler<>(
				QuestionFunction.class);
		HTTPRequestSignatureVerificationEnabledHandler<String, List<Question>> handlerSpy = Mockito.spy(handler);
      Mockito.doReturn(mockSystemService).when(handlerSpy).getSystemService();
		List<Question> getQuestionResult = (List<Question>) handlerSpy.handleRequest(mockMessage,TestConfig.PRECONFIGURED_QUESTION.getDifficulty(), mockExecContext);
		handlerSpy.close();
		assertEquals(getQuestionResult, new ArrayList<Question>(Arrays.asList(TestConfig.PRECONFIGURED_QUESTION)));

	}
	
	@Test
	public void testQuestionHandlerGetQuestionWhenEmpty() {
		 when(mockExecContext.getFunctionName()).thenReturn("getQuestion");
		AzureSpringBootRequestHandler<Question, Question> handler = new AzureSpringBootRequestHandler<>(
				QuestionFunction.class);
		Question getQuestionResult = handler.handleRequest(testQuestion1, mockExecContext);
		handler.close();
		//assertEquals(getQuestionResult, Question.EMPTY_DATABASE);

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
		 HTTPRequestSignatureVerificationEnabledHandler<String, Question> handler = new HTTPRequestSignatureVerificationEnabledHandler<>(
				QuestionFunction.class);
		HTTPRequestSignatureVerificationEnabledHandler<String, Question> handlerSpy = Mockito.spy(handler);
       Mockito.doReturn(mockSystemService).when(handlerSpy).getSystemService();
		Question getQuestionResult = (Question) handlerSpy.handleRequest(mockMessage,TestConfig.PRECONFIGURED_QUESTION.getId(), mockExecContext);
		handlerSpy.close();
		assertEquals(getQuestionResult, TestConfig.PRECONFIGURED_QUESTION);

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
		handler.handleRequest(TestConfig.PRECONFIGURED_QUESTION, mockExecContext);
		 assertDoesNotThrow(() -> handler.handleRequest(TestConfig.PRECONFIGURED_QUESTION, mockExecContext));
		handler.close();
		

	}




}
