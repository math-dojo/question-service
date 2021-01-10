package io.mathdojo.questionservice.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.mathdojo.questionservice.model.Question;
import io.mathdojo.questionservice.model.Topic;

@Configuration
public class FunctionBeans {
	/**
	 * This class contains all the functions that can be called in this service.
	 * Functions are called through RequestHandler classes
	 */

	@Autowired
	public MathDojoQuestionRepository qRepository;
	@Autowired
	public MathDojoTopicRepository tRepository;

	@Bean
	public Function<Question, List<Question>> searchQuestionsByTitleAndDifficulty() {
		return input -> {
			return qRepository.findByQuestionTitle(input.getQuestionTitle()).stream()
					.filter(q -> q.getDifficulty().equals(input.getDifficulty())).collect(Collectors.toList());

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
				Topic oldTopic = tRepository.findById(topic.getId()).get();
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