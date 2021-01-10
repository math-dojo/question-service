package io.mathdojo.questionservice.handlers;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.microsoft.azure.functions.ExecutionContext;

import io.mathdojo.questionservice.configuration.FunctionBeans;
import io.mathdojo.questionservice.model.Topic;

public class TopicHandlerTest {
	private Topic topic = new Topic();
	@Ignore
	@Test
	public void testTopicHandler() {
		AzureSpringBootRequestHandler<Topic, Topic> handler = new AzureSpringBootRequestHandler<>(
				FunctionBeans.class);
		Topic getTopicResult = handler.handleRequest(topic, getExecutionContext("getTopic"));
		Topic getTopicByIdResult = handler.handleRequest(topic, getExecutionContext("getTopic"));

		handler.close();
		assertEquals(getTopicResult, Topic.EMPTY_DATABASE);
		assertEquals(getTopicByIdResult, Topic.EMPTY_DATABASE);
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
