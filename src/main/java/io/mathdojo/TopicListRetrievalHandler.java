package io.mathdojo;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

public class TopicListRetrievalHandler extends AzureSpringBootRequestHandler<Topic, List<Question>>{	
	
	@FunctionName("getQuestions")
	public List<Question> executeGetQuestions(@HttpTrigger(name = "request", methods = {
			HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS, route = "topics/{topicId}/questions") HttpRequestMessage<Optional<Topic>> request,
			ExecutionContext context, @BindingName("topicId") String topicId) {
		context.getLogger().info("Retrieving Topic Questions");
		Topic topic = new Topic();
		topic.setId(topicId);
		return handleRequest(topic, context);
	}
}
