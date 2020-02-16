package io.mathdojo;

import java.util.function.Function;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.mathdojo.model.GetObject;
import io.mathdojo.model.Question;
import io.mathdojo.model.QuestionRepository;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class QuestionFunction {
	

	@Autowired
	private QuestionRepository repository;
	
	
	private Question testQuestion = new Question("Our first question", "Is it really happenning?",
			"It may be happening", new String[] { "Does it snow in canada", "Does it rain in london", "Yes!" });

	

	public static void main(String[] args) throws Exception {
		SpringApplication.run(QuestionFunction.class, args);
	}

	@Bean
	public Function<GetObject, Question> getQuestion() {
		final Mono<Question> saveUserMono = repository.save(testQuestion);
		return get -> saveUserMono.block();
	}

	@Bean
	public Function<Question,  Mono<Question>> createQuestion() {		
		return question -> repository.save(question);
	}

	@PostConstruct
	public void setup() {

		repository.deleteAll().block();
	}

	@PreDestroy
	public void cleanup() {

		repository.deleteAll().block();
	}

}