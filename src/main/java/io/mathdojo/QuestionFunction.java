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
    public Function<GetObject, Question> question() {
        return get -> new Question(get.get()+"deolu" );
    }
//	@PostMapping("/postQuestion")
//	public Question greeting(@RequestBody Map<String, String> question ) {
//	    String title = question.get("title");
//	    String body = question.get("title");
//	    String sampleAnswer = question.get("sampleAnswer");
//	    String[] hints = question.get("hints").split(",") ;
//	    
//		return qRepo.save(new Question(title,body,sampleAnswer,hints));
//	}

}