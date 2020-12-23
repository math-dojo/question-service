package io.mathdojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
	public Function<Question, Question> getQuestion() {
		return input -> Iterables.getFirst(
				qRepository.findByQuestionTitle(input.getQuestionTitle()).stream()
						.filter(i -> i.getDifficulty().equals(input.getDifficulty())).collect(Collectors.toList()),
				Question.EMPTY_DATABASE);
	}

	@Bean
	public Function<Question, Question> createQuestion() {
		return question -> qRepository.save(question);
	}

	@Bean
	public Function<Question, Question> getQuestionById() {
		return question -> qRepository.findById(question.getId()).isPresent()
				? qRepository.findById(question.getId()).get()
				: Question.EMPTY_DATABASE;
	}

	@Bean
	public Function<Question, Question> updateQuestion() {
		return new Function<Question, Question>() {

			@Override
			public Question apply(Question question) {
				Question oldQuestion = qRepository.findById(question.getId()).isPresent()
						? qRepository.findById(question.getId()).get()
						: null;
				Question newQuestion = new Question(question.getId(),
						question.getQuestionTitle() != null ? question.getQuestionTitle()
								: oldQuestion.getQuestionTitle(),
						question.getQuestionBody() != null ? question.getQuestionBody() : oldQuestion.getQuestionBody(),
						question.getSampleAnswer() != null ? question.getSampleAnswer() : oldQuestion.getSampleAnswer(),
						question.getSuccessRate() != null ? question.getSuccessRate() : oldQuestion.getSuccessRate(),
						question.getDifficulty() != null ? question.getDifficulty() : oldQuestion.getDifficulty(),
						question.getHints() != null ? question.getHints() : oldQuestion.getHints(),
						question.getParentTopicTitle() != null ? question.getParentTopicTitle()
								: oldQuestion.getParentTopicTitle(),
						question.getQuestionAnswerOptions() != null ? question.getQuestionAnswerOptions()
								: oldQuestion.getQuestionAnswerOptions(),
						question.getAnswer() != null ? question.getAnswer() : oldQuestion.getAnswer());
				;
				return qRepository.save(newQuestion);
			}

		};
	}

	@Bean
	public Consumer<Question> deleteQuestion() {
		return question -> qRepository.deleteById(question.getId());
	}

	@Bean
	public Function<Topic, Topic> getTopic() {
		return input -> Iterables.getFirst(
				tRepository.findByTopicTitle(input.getTopicTitle()).stream().collect(Collectors.toList()),
				Topic.EMPTY_DATABASE);
	}

	@Bean
	public Function<Topic, Topic> createTopic() {
		return topic -> tRepository.save(topic);
	}

	@Bean
	public Function<Topic, Topic> getTopicById() {
		return topic -> tRepository.findById(topic.getId()).isPresent() ? tRepository.findById(topic.getId()).get()
				: Topic.EMPTY_DATABASE;
	}

	@Bean
	public Function<Topic, Topic> updateTopic() {

		return new Function<Topic, Topic>() {

			@Override
			public Topic apply(Topic topic) {
				Topic oldTopic = tRepository.findById(topic.getId()).isPresent()
						? tRepository.findById(topic.getId()).get()
						: null;
				Topic newTopic = new Topic(topic.getId(),
						topic.getTopicTitle() != null ? topic.getTopicTitle() : oldTopic.getTopicTitle(),
						topic.getName() != null ? topic.getName() : oldTopic.getName(),
						topic.getQuestions() != null ? topic.getQuestions() : oldTopic.getQuestions());
				return tRepository.save(newTopic);
			}

		};
	}

	@Bean
	public Consumer<Topic> deleteTopic() {
		return topic -> tRepository.deleteById(topic.getId());
	}

	@Bean
	public Function<Topic, List<Question>> getQuestions() {

		return new Function<Topic, List<Question>>() {
			@Override
			public List<Question> apply(Topic t) {
				List<Question> x = new ArrayList<>();
				Optional<Topic> topicWrap = tRepository.findById(t.getId());
				if(!topicWrap.isPresent()) {
					throw new QuestionServiceException("topic not found");
				}
				qRepository.findAllById(topicWrap.get().getQuestions()).forEach(x::add);
				return x;
			}
		};
	}

}