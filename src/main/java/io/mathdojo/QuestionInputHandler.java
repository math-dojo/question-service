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

public class QuestionInputHandler extends HTTPRequestSignatureVerificationEnabledHandler<Question, Question> {
	@FunctionName("createQuestion")
	public HttpResponseMessage execute(@HttpTrigger(name = "request", methods = {
			HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS, route = "questions") HttpRequestMessage<Optional<Question>> request,
			ExecutionContext context) {
		context.getLogger().info("Creating question");
		try {
			Object handledRequest = handleRequest(request, request.getBody().get(), context);
			if (handledRequest instanceof HttpResponseMessage) {
				return (HttpResponseMessage) handledRequest;
			}
			return request.createResponseBuilder(HttpStatus.CREATED).body(handledRequest).build();

		} catch (QuestionServiceException e) {
			return request.createResponseBuilder(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			context.getLogger().log(Level.WARNING, "Question creation failed", e);
			return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	@FunctionName("updateQuestion")
	public HttpResponseMessage executeUpdate(@HttpTrigger(name = "request", methods = {
			HttpMethod.PUT }, authLevel = AuthorizationLevel.ANONYMOUS, route = "questions/{questionId}") HttpRequestMessage<Optional<Question>> request,
			ExecutionContext context, @BindingName("questionId") String questionId) {

		context.getLogger().info("Updating question");
		try {
			Question toUpdate = request.getBody().get();
			if (!questionId.equals(toUpdate.getId())) {
				return request.createResponseBuilder(HttpStatus.BAD_REQUEST).build();
			}
			Object handledRequest = handleRequest(request, toUpdate, context);
			if (handledRequest instanceof HttpResponseMessage) {
				return (HttpResponseMessage) handledRequest;
			}
			
			return request.createResponseBuilder(HttpStatus.ACCEPTED).body(handledRequest).build();

		} catch (QuestionServiceException e) {
			return request.createResponseBuilder(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			context.getLogger().log(Level.WARNING, "Question Update failed", e);
			return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
