package io.mathdojo;

import java.util.Optional;

import javax.servlet.HttpMethodConstraintElement;

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
 * This class handles http requests specific to questions
 *
 */

public class QuestionHandler extends AzureSpringBootRequestHandler<Question, Question> {

	@FunctionName("getQuestionsByTitleAndDifficulty")
	public Question executeGet(@HttpTrigger(name = "request", methods = {
			HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS, route = "question") HttpRequestMessage<Optional<Question>> request,
			ExecutionContext context) {
		context.getLogger().info("Retrieving question");

		return handleRequest(new Question(request.getQueryParameters()), context);
	}

	@FunctionName("createQuestion")
	public Question execute(@HttpTrigger(name = "request", methods = {
			HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS, route = "question") HttpRequestMessage<Optional<Question>> request,
			ExecutionContext context) {
		context.getLogger().info("Posting question with title: " + request.getBody().get().getQuestionTitle());
		return handleRequest(request.getBody().get(), context);
	}

	@FunctionName("getQuestionById")
	public Question executeGetById(@HttpTrigger(name = "request", methods = {
			HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS, route = "questions/{questionId}") HttpRequestMessage<Optional<Question>> request,
			ExecutionContext context, @BindingName("questionId") String questionId) {
		context.getLogger().info("Retrieving question");
		Question question = new Question();
		question.setId(questionId);

		return handleRequest(question, context);
	}

	@FunctionName("updateQuestion")
	public Question executeUpdate(
			@HttpTrigger(name = "request", methods = {
					HttpMethod.PUT }, authLevel = AuthorizationLevel.ANONYMOUS, route = "questions/{questionId}") HttpRequestMessage<Optional<Question>> request,
			ExecutionContext context, @BindingName("questionId") String questionId) {

		context.getLogger().info("Updating question with title: " + request.getBody().get().getQuestionTitle());
		request.getBody().get().setId(questionId);
		return handleRequest(request.getBody().get(), context);
	}

	@FunctionName("deleteQuestionById")
	public Question executeDelete(@HttpTrigger(name = "request", methods = {
			HttpMethod.DELETE }, authLevel = AuthorizationLevel.ANONYMOUS, route = "questions/{questionId}") HttpRequestMessage<Optional<Question>> request,
			ExecutionContext context, @BindingName("questionId") String questionId) {
		context.getLogger().info("Deleting question with title: ");
		Question question = new Question();
		question.setId(questionId);
		return handleRequest(question, context);
	}

}