package io.mathdojo;

import java.util.Optional;

import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

public class GetQuestionHandler extends AzureSpringBootRequestHandler<GetObject,Question> {

    @FunctionName("getQuestion")
    public Question execute(
            @HttpTrigger(name = "request", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<Question>> request,
            ExecutionContext context) {
    	context.getLogger().info("Retrieving question" );
        return handleRequest(new GetObject(), context);
    }
    
 
}