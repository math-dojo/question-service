package io.mathdojo;

import java.util.Optional;

import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

/**
 * 
 * This class handles http requests specific to topics
 *
 */
public class TopicHandler extends AzureSpringBootRequestHandler<Topic, Topic> {

	@FunctionName("getTopic")
	public Topic executeGet(@HttpTrigger(name = "request", methods = {
			HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS, route = "topics") HttpRequestMessage<Optional<Topic>> request,
			ExecutionContext context) {
		context.getLogger().info("Retrieving topic");
		return handleRequest(new Topic(request.getQueryParameters()), context);
	}

	@FunctionName("createTopic")
	public Topic executePost(@HttpTrigger(name = "request", methods = {
			HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS, route = "topics") HttpRequestMessage<Optional<Topic>> request,
			ExecutionContext context) {
		context.getLogger().info("Posting topic with title: " + request.getBody().get().getTopicTitle());
		return handleRequest(request.getBody().get(), context);
	}

	@FunctionName("getTopicById")
	public Topic executeGetById(@HttpTrigger(name = "request", methods = {
			HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS, route = "topics/{topicId}") HttpRequestMessage<Optional<Topic>> request,
			ExecutionContext context, @BindingName("topicId") String topicId) {
		context.getLogger().info("Retrieving topic");
		Topic topic = new Topic();
		topic.setId(topicId);

		return handleRequest(topic, context);
	}

	@FunctionName("updateTopic")
	public Topic executeUpdate(
			@HttpTrigger(name = "request", methods = {
					HttpMethod.PUT }, authLevel = AuthorizationLevel.ANONYMOUS, route = "topics/{topicId}") HttpRequestMessage<Optional<Topic>> request,
			ExecutionContext context, @BindingName("topicId") String topicId) {

		context.getLogger().info("Updating topic with title: " + request.getBody().get().getTopicTitle());
		request.getBody().get().setId(topicId);
		return handleRequest(request.getBody().get(), context);
	}

	@FunctionName("deleteTopic")
	public Topic executeDelete(@HttpTrigger(name = "request", methods = {
			HttpMethod.DELETE }, authLevel = AuthorizationLevel.ANONYMOUS, route = "topics/{topicId}") HttpRequestMessage<Optional<Topic>> request,
			ExecutionContext context, @BindingName("topicId") String topicId) {
		context.getLogger().info("Deleting topic with title: ");
		Topic topic = new Topic();
		topic.setId(topicId);
		return handleRequest(topic, context);
	}

	@FunctionName("getQuestions")
	public Topic executeGetQuestions(@HttpTrigger(name = "request", methods = {
			HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS, route = "topics/{topicId}/questions") HttpRequestMessage<Optional<Topic>> request,
			ExecutionContext context, @BindingName("topicId") String topicId) {
		context.getLogger().info("Retrieving Topic Questions");
		Topic topic = new Topic();
		topic.setId(topicId);

		return handleRequest(topic, context);
	}

}