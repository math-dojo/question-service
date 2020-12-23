package io.mathdojo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
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
public class TopicHandlerTest {
	private ExecutionContext mockExecContext;
	private Topic testTopic1 = new Topic("test-topic-1", "test topic 1", "test", new ArrayList<String>());
	@Before
	 public void setUp() {
         Logger testLogger = mock(Logger.class);
         mockExecContext = mock(ExecutionContext.class);
         Mockito.when(mockExecContext.getLogger()).thenReturn(testLogger);

 }
	@Test
	public void testTopicHandlerGetTopic() {
		 when(mockExecContext.getFunctionName()).thenReturn("getTopic");
		AzureSpringBootRequestHandler<Topic, Topic> handler = new AzureSpringBootRequestHandler<>(
				QuestionFunction.class);
		Topic getTopicResult = handler.handleRequest(TestConfig.PRECONFIGURED_TOPIC, mockExecContext);
		handler.close();
		assertEquals(getTopicResult, TestConfig.PRECONFIGURED_TOPIC);

	}
	
	@Test
	public void testTopicHandlerGetTopicWhenEmpty() {
		 when(mockExecContext.getFunctionName()).thenReturn("getTopic");
		AzureSpringBootRequestHandler<Topic, Topic> handler = new AzureSpringBootRequestHandler<>(
				QuestionFunction.class);
		Topic getTopicResult = handler.handleRequest(testTopic1, mockExecContext);
		handler.close();
		assertEquals(getTopicResult, Topic.EMPTY_DATABASE);

	}
	
	@Test
	public void testTopicHandlerCreateTopic() {
		 when(mockExecContext.getFunctionName()).thenReturn("createTopic");
		AzureSpringBootRequestHandler<Topic, Topic> handler = new AzureSpringBootRequestHandler<>(
				QuestionFunction.class);
		Topic getTopicResult = handler.handleRequest(testTopic1, mockExecContext);
		handler.close();
		assertEquals(getTopicResult, testTopic1);


	}
	
	@Test
	public void testTopicHandlerGetTopicById() {
		 when(mockExecContext.getFunctionName()).thenReturn("getTopicById");
		AzureSpringBootRequestHandler<Topic, Topic> handler = new AzureSpringBootRequestHandler<>(
				QuestionFunction.class);
		Topic getTopicResult = handler.handleRequest(TestConfig.PRECONFIGURED_TOPIC, mockExecContext);
		handler.close();
		assertEquals(getTopicResult, TestConfig.PRECONFIGURED_TOPIC);

	}
	
	@Test
	public void testTopicHandlerUpdateTopic() {
		 when(mockExecContext.getFunctionName()).thenReturn("updateTopic");
		AzureSpringBootRequestHandler<Topic, Topic> handler = new AzureSpringBootRequestHandler<>(
				QuestionFunction.class);
		Topic newTestTopic = new Topic("test-topic", "", "", new ArrayList<String>());
		Topic getTopicResult = handler.handleRequest(newTestTopic, mockExecContext);
		handler.close();
		assertEquals(getTopicResult, newTestTopic);


	}
	
	@Test
	public void testTopicHandlerDeleteTopicThrowsNoErrorIfSuccessful() {
		 when(mockExecContext.getFunctionName()).thenReturn("deleteTopic");
		AzureSpringBootRequestHandler<Topic, Topic> handler = new AzureSpringBootRequestHandler<>(
				QuestionFunction.class);
		handler.handleRequest(TestConfig.PRECONFIGURED_TOPIC, mockExecContext);
		 assertDoesNotThrow(() -> handler.handleRequest(TestConfig.PRECONFIGURED_TOPIC, mockExecContext));
		handler.close();
		

	}

    @Test
	public void testTopicHandlerGetQuestions() {
		 when(mockExecContext.getFunctionName()).thenReturn("getQuestions");
		AzureSpringBootRequestHandler<Topic, List<Question>> handler = new AzureSpringBootRequestHandler<>(
				QuestionFunction.class);
		List<Question> getQuestionsResult = handler.handleRequest(TestConfig.PRECONFIGURED_TOPIC, mockExecContext);
		handler.close();
		assertEquals(getQuestionsResult, new ArrayList<>());

	}




}
