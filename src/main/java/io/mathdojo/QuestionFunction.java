package io.mathdojo;

import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.mathdojo.model.GetObject;
import io.mathdojo.model.Question;

@SpringBootApplication
public class QuestionFunction {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(QuestionFunction.class, args);
    }

    
   
    @Bean
    public Function<GetObject, Question> getQuestion() {
        return get -> new Question("Our first question", "Is it really happenning?", "It may be happening", new String[]{"Does it snow in canada", "Does it rain in london", "Yes!"});
    }
	@Bean
    public Function<Question, String> createQuestion() { 
		 //save question to database here
        new Question("Our first question", "Is it really happenning?", "It may be happening", new String[]{"Does it snow in canada", "Does it rain in london", "Yes!"});
        return question -> question.getQuestionTitle();
    }

}