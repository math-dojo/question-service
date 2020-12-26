package io.mathdojo;

import java.util.List;
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

public class QuestionListOutputHandler extends HTTPRequestSignatureVerificationEnabledHandler<String, List<Question>> {
	@FunctionName("getQuestionsByDifficulty")
	public HttpResponseMessage executeGet(@HttpTrigger(name = "request", methods = {
			HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS, route = "questions/difficulty/{difficulty}") HttpRequestMessage<Optional<String>> request,
			ExecutionContext context, @BindingName("difficulty") String difficulty) {
		context.getLogger().info("Retrieving questions of difficulty " + difficulty);
		try {
			Object handledRequest = handleRequest(request, difficulty, context);
			if (handledRequest instanceof HttpResponseMessage) {
				return (HttpResponseMessage) handledRequest;
			}
			return request.createResponseBuilder(HttpStatus.OK).body(handledRequest).build();

		} catch (QuestionServiceException e) {
			return request.createResponseBuilder(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			context.getLogger().log(Level.WARNING, "Question retrieval failed", e);
			return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}