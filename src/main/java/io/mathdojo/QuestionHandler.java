package io.mathdojo;


import io.mathdojo.model.GetObject;
import io.mathdojo.model.Question;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@RestController
public class QuestionHandler extends AzureSpringBootRequestHandler<GetObject,Question> {

    @FunctionName("getQuestion")
    public Question execute(
            @HttpTrigger(name = "request", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<Question>> request,
            ExecutionContext context) {

        //context.getLogger().info("Greeting user name: " + request.getBody().get().getName());
        return handleRequest(new GetObject(), context);
    }
    
 
}