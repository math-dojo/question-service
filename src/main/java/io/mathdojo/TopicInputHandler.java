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

public class TopicInputHandler extends HTTPRequestSignatureVerificationEnabledHandler<Topic, Topic> {

	@FunctionName("createTopic")
	public HttpResponseMessage executePost(@HttpTrigger(name = "request", methods = {
			HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS, route = "topics") HttpRequestMessage<Optional<Topic>> request,
			ExecutionContext context) {
		context.getLogger().info("Creating topic");
		try {
			Object handledRequest = handleRequest(request, request.getBody().get(), context);
			if (handledRequest instanceof HttpResponseMessage) {
				return (HttpResponseMessage) handledRequest;
			}
			return request.createResponseBuilder(HttpStatus.CREATED).body(handledRequest).build();

		} catch (QuestionServiceException e) {
			return request.createResponseBuilder(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			context.getLogger().log(Level.WARNING, "Topic creation failed", e);
			return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@FunctionName("updateTopic")
	public HttpResponseMessage executeUpdate(@HttpTrigger(name = "request", methods = {
			HttpMethod.PUT }, authLevel = AuthorizationLevel.ANONYMOUS, route = "topics/{topicId}") HttpRequestMessage<Optional<Topic>> request,
			ExecutionContext context, @BindingName("topicId") String topicId) {

		context.getLogger().info("Updating topic");
		try {
			Object handledRequest = handleRequest(request, request.getBody().get(), context);
			if (handledRequest instanceof HttpResponseMessage) {
				return (HttpResponseMessage) handledRequest;
			}
			return request.createResponseBuilder(HttpStatus.ACCEPTED).body(handledRequest).build();

		} catch (QuestionServiceException e) {
			return request.createResponseBuilder(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			context.getLogger().log(Level.WARNING, "Topic Update failed", e);
			return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
