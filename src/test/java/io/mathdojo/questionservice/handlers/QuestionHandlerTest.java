package io.mathdojo.questionservice.handlers;

import static org.junit.Assert.assertEquals;

import java.net.URISyntaxException;
import java.util.logging.Logger;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.microsoft.azure.functions.ExecutionContext;

import io.mathdojo.questionservice.configuration.FunctionBeans;
import io.mathdojo.questionservice.model.Question;

public class QuestionHandlerTest {
	private Question question = new Question();

	@Ignore
	@Test
	public void testQuestionHandler() throws URISyntaxException {
		AzureSpringBootRequestHandler<Question, Question> handler = new AzureSpringBootRequestHandler<>(
				FunctionBeans.class);
		Question getQuestionResult = handler.handleRequest(question, getExecutionContext("getQuestion"));
		Question getQuestionByIdResult = handler.handleRequest(question, getExecutionContext("getQuestionById"));
		handler.close();
		assertEquals(getQuestionResult, Question.EMPTY_DATABASE);
		assertEquals(getQuestionByIdResult, Question.EMPTY_DATABASE);

	}
	
	private ExecutionContext getExecutionContext(String function) {

		return new ExecutionContext() {

			@Override
			public Logger getLogger() {
				return Logger.getAnonymousLogger();
			}

			@Override
			public String getInvocationId() {
				return function;
			}

			@Override
			public String getFunctionName() {
				return function;
			}
		};
	}
}
