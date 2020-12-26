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
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import io.mathdojo.useraccountservice.security.HTTPRequestSignatureVerificationEnabledHandler;

public class TopicListOutputHandler extends HTTPRequestSignatureVerificationEnabledHandler<String, List<Topic>> {

	@FunctionName("getTopics")
	public HttpResponseMessage executeGetQuestions(@HttpTrigger(name = "request", methods = {
			HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS, route = "topics") HttpRequestMessage<Optional<String>> request,
			ExecutionContext context) {
		context.getLogger().info("Retrieving Topic Questions");
		try {
			Object handledRequest = handleRequest(request, "", context);
			if (handledRequest instanceof HttpResponseMessage) {
				return (HttpResponseMessage) handledRequest;
			}
			return request.createResponseBuilder(HttpStatus.OK).body(handledRequest).build();

		} catch (Exception e) {
			context.getLogger().log(Level.WARNING, "Topic retrieval failed", e);
			return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}