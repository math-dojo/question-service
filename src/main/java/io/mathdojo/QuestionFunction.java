package io.mathdojo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.common.collect.Iterables;

@SpringBootApplication
public class QuestionFunction {

	@Autowired
	public MathDojoQuestionRepository repository;
	@Autowired
	public MathDojoTopicRepository tRepository;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(QuestionFunction.class, args);
	}

	@Bean
	public Function<Question, Question> getQuestion() {
		return input -> Iterables.getFirst(
				repository.findByQuestionTitle(input.getQuestionTitle()).stream()
						.filter(i -> "easy".equals(i.getDifficulty())).collect(Collectors.toList()),
				Question.EMPTY_DATABASE);
	}

	@Bean
	public Consumer<Question> createQuestion() {
		return question -> repository.save(question);
	}

	@Bean
	public Function<Question, Question> getQuestionById() {
		return question -> repository.findById(question.getId()).isPresent() ? repository.findById(question.getId()).get() : Question.EMPTY_DATABASE;
	}
	
	@Bean
	public Consumer<Question> updateQuestion() {
		return createQuestion();
	}
	@Bean
	public Consumer<Question> deleteQuestion() {
		return question -> repository.deleteById(question.getId());
	}
	
	@Bean
	public Function<Topic, Topic> getTopic() {
		return input -> Iterables.getFirst(
				tRepository.findByTopicTitle(input.getTopicTitle()).stream().collect(Collectors.toList()),
				Topic.EMPTY_DATABASE);
	}

	@Bean
	public Consumer<Topic> createTopic() {
		return topic -> tRepository.save(topic);
	}

	@Bean
	public Function<Topic, Topic> getTopicById() {
		return topic -> tRepository.findById(topic.getId()).isPresent() ? tRepository.findById(topic.getId()).get() : Topic.EMPTY_DATABASE;
	}
	
	@Bean
	public Consumer<Topic> updateTopic() {
		return createTopic();
	}
	@Bean
	public Consumer<Topic> deleteTopic() {
		return topic -> repository.deleteById(topic.getId());
	}
	@Bean
	public Function<Topic, List<Question>> getQuestions() {

		return new Function<Topic, List<Question>>() {
			@Override
			public List<Question> apply(Topic t) {
				List<Question> x = new ArrayList<>();
				repository.findAllById(tRepository.findById(t.getId()).get().getQuestions()).forEach(x::add);
				return x;
			}
		};
	}

}