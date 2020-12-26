package io.mathdojo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuestionFunction {
	/**
	 * This class contains all the functions that can be called in this service.
	 * Functions are called through RequestHandler classes
	 */

	@Autowired
	public MathDojoQuestionRepository qRepository;
	@Autowired
	public MathDojoTopicRepository tRepository;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(QuestionFunction.class, args);
	}

	@Bean
	public Function<String, List<Question>> getQuestionsByDifficulty() {
		return input -> {
			if (input.equalsIgnoreCase("easy") || input.equalsIgnoreCase("medium") || input.equalsIgnoreCase("hard")) {
				return qRepository.findByDifficulty(input);

			} else {
				throw new QuestionServiceException(QuestionServiceException.UNSUPPORTED_MESSAGE);

			}
		};
	}

	@Bean
	public Function<Question, Question> createQuestion() {
		return question -> {
			if (qRepository.existsById(question.getId())) {
				throw new QuestionServiceException(QuestionServiceException.ALREADY_EXISTS_MESSAGE);
			}
			return qRepository.save(question);
		};
	}

	@Bean
	public Function<String, Question> getQuestionById() {
		return input -> {
			if (!qRepository.existsById(input)) {
				throw new QuestionServiceException(QuestionServiceException.NOT_FOUND_MESSAGE);
			}
			return qRepository.findById(input).get();

		};
	}

	@Bean
	public Function<Question, Question> updateQuestion() {
		return new Function<Question, Question>() {

			@Override
			public Question apply(Question question) {
				if (!qRepository.existsById(question.getId())) {
					throw new QuestionServiceException(QuestionServiceException.NOT_FOUND_MESSAGE);
				}
				Question oldQuestion = qRepository.findById(question.getId()).get();
				return qRepository.save(oldQuestion.updateNonNullAttributes(question));
			}

		};
	}

	@Bean
	public Consumer<String> deleteQuestion() {
		return input -> {
			if (!qRepository.existsById(input)) {
				throw new QuestionServiceException(QuestionServiceException.NOT_FOUND_MESSAGE);
			}
			qRepository.deleteById(input);

		};
	}

	@Bean
	public Function<Topic, Topic> createTopic() {
		return topic -> {
			if (tRepository.existsById(topic.getId())) {
				throw new QuestionServiceException(QuestionServiceException.ALREADY_EXISTS_MESSAGE);
			}
			return tRepository.save(topic);
		};
	}

	@Bean
	public Function<String, Topic> getTopicById() {
		return input -> {
			if (!tRepository.existsById(input)) {
				throw new QuestionServiceException(QuestionServiceException.NOT_FOUND_MESSAGE);
			}
			return tRepository.findById(input).get();

		};
	}

	@Bean
	public Function<Topic, Topic> updateTopic() {

		return new Function<Topic, Topic>() {


			@Override
			public Topic apply(Topic topic) {
				if (!tRepository.existsById(topic.getId())) {
					throw new QuestionServiceException(QuestionServiceException.NOT_FOUND_MESSAGE);
				}
				Topic oldTopic =  tRepository.findById(topic.getId()).get();
				return tRepository.save(oldTopic.updateNonNullAttributes(topic));
			}

		};
	}

	@Bean
	public Consumer<String> deleteTopic() {
		return input -> {
			if (!tRepository.existsById(input)) {
				throw new QuestionServiceException(QuestionServiceException.NOT_FOUND_MESSAGE);
			}
			tRepository.deleteById(input);

		};
		
	}
	
	@Bean
	public Supplier<List<Topic>> getTopics() {
		return new Supplier<List<Topic>>() {
			@Override
			public List<Topic> get() {
				return tRepository.findAll();
			}
			
		};	
	}

	@Bean
	public Function<String, List<Question>> getQuestions() {

		return new Function<String, List<Question>>() {
			@Override
			public List<Question> apply(String t) {
				List<Question> x = new ArrayList<>();
				if (!tRepository.existsById(t)) {
					throw new QuestionServiceException(QuestionServiceException.NOT_FOUND_MESSAGE);
				}
				qRepository.findAllById(tRepository.findById(t).get().getQuestions()).forEach(x::add);
				return x;
			}
		};
	}

}