package io.mathdojo;

import java.util.Optional;
import java.util.logging.Level;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import io.mathdojo.useraccountservice.security.HTTPRequestSignatureVerificationEnabledHandler;

/**
 * 
 * This class handles http requests specific to topics
 *
 */
public class TopicStringInputHandler extends HTTPRequestSignatureVerificationEnabledHandler<String, Topic> {

	@FunctionName("getTopicById")
	public HttpResponseMessage executeGetById(@HttpTrigger(name = "request", methods = {
			HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS, route = "topics/{topicId}") HttpRequestMessage<Optional<String>> request,
			ExecutionContext context, @BindingName("topicId") String topicId) {
		context.getLogger().info("Retrieving topic");
		try {
			Object handledRequest = handleRequest(request, topicId, context);
			if (handledRequest instanceof HttpResponseMessage) {
				return (HttpResponseMessage) handledRequest;
			}

			return request.createResponseBuilder(HttpStatus.OK).body(handledRequest).build();

		} catch (QuestionServiceException e) {
			return request.createResponseBuilder(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			context.getLogger().log(Level.WARNING, "Question retrieval failed", e);
			return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@FunctionName("deleteTopic")
	public HttpResponseMessage executeDelete(@HttpTrigger(name = "request", methods = {
			HttpMethod.DELETE }, authLevel = AuthorizationLevel.ANONYMOUS, route = "topics/{topicId}") HttpRequestMessage<Optional<String>> request,
			ExecutionContext context, @BindingName("topicId") String topicId) {
		context.getLogger().info("Deleting topic");
		try {
			Object handledRequest = handleRequest(request, topicId, context);
			if (handledRequest instanceof HttpResponseMessage) {
				return (HttpResponseMessage) handledRequest;
			}
			return request.createResponseBuilder(HttpStatus.NO_CONTENT).body("").build();

		} catch (QuestionServiceException e) {
			return request.createResponseBuilder(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			context.getLogger().log(Level.WARNING, "Topic deletion failed", e);
			return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}







}