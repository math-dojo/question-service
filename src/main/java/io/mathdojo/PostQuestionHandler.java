package io.mathdojo;

import java.util.Optional;

import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

public class PostQuestionHandler extends AzureSpringBootRequestHandler<Question,String> {

    @FunctionName("createQuestion")
    public String execute(
            @HttpTrigger(name = "request", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<Question>> request,
            ExecutionContext context) {
    	context.getLogger().info("Posting question with title: " + request.getBody().get().getQuestionTitle());
        return handleRequest(request.getBody().get(), context);
    }
    
 
}