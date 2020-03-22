package io.mathdojo;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.common.collect.Iterables;

@SpringBootApplication
public class QuestionFunction {

	@Autowired
	public MathDojoQuestionRepository repository;
	private final Question emptyDatabaseReturnValue = new Question("the database is empty", null, null, null, 0, null, false);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(QuestionFunction.class, args);
	}

	@Bean
	public Function<GetObject, Question> getQuestion() {
		//customise the query here
		return get ->  Iterables.getLast(repository.findAll() , emptyDatabaseReturnValue) ;
	}

	@Bean
	public Function<Question,  String> createQuestion() {	
		return question -> repository.save(question).getQuestionTitle()  ;

	}

}