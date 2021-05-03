package io.mathdojo;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microsoft.azure.functions.ExecutionContext;

import reactor.core.publisher.Flux;

@Configuration
public class QuestionFunction {

	@Autowired
	public QuestionService questionService;

	@Bean
	public Function<Flux<Question>, Flux<List<Question>>> getQuestionsByTitleAndDifficulty() {
		return questionFluxEntity -> {
			return questionFluxEntity.map(question -> {
				return questionService.findQuestionsByTitleAndDifficulty(question.getQuestionTitle(),
						question.getDifficulty());
			});
		};
	}

	@Bean
	public Function<Flux<Question>, Flux<Question>> createQuestion(ExecutionContext context) {
		return questionFluxEntity -> {
			return questionFluxEntity.map(question -> {
				context.getLogger().info("creating question with title " + question.getQuestionTitle());
				return questionService.createQuestion(question);
			});
		};
	}

	@Bean
	public Function<Flux<String>, Flux<Question>> getQuestionById(ExecutionContext context) {
		return questionFluxEntity -> {
			return questionFluxEntity.map(questionId -> {
				context.getLogger().info("retrieving question with id " + questionId);
				return questionService.getQuestionById(questionId);
			});
		};

	}

	@Bean
	public Function<Flux<Question>, Flux<Question>> updateQuestion(ExecutionContext context) {
		return questionFluxEntity -> {
			return questionFluxEntity.map(question -> {
				context.getLogger().info("updating question with id " + question.getId());
				return questionService.updateQuestionById(question);
			});
		};
	}

	@Bean
	public Consumer<Question> deleteQuestionById(ExecutionContext context) {
		return deletionRequest -> questionService.deleteQuestionById(deletionRequest.getId());
	}

	@Bean
	public Function<Flux<Topic>, Flux<List<Topic>>> getTopicsByTitle() {
		return topicFluxEntity -> {
			return topicFluxEntity.map(topic -> {
				return questionService.findTopicsByTitle(topic.getTopicTitle());
			});
		};
	}

	@Bean
	public Function<Flux<Topic>, Flux<Topic>> createTopic(ExecutionContext context) {
		return topicFluxEntity -> {
			return topicFluxEntity.map(topic -> {
				context.getLogger().info("creating topic with title " + topic.getTopicTitle());
				return questionService.createTopic(topic);
			});
		};
	}

	@Bean
	public Function<Flux<String>, Flux<Topic>> getTopicById(ExecutionContext context) {
		return topicFluxEntity -> {
			return topicFluxEntity.map(topicId -> {
				context.getLogger().info("retrieving topic with id " + topicId);
				return questionService.getTopicById(topicId);
			});
		};
	}

	@Bean
	public Function<Flux<Topic>, Flux<Topic>> updateTopic(ExecutionContext context) {
		return topicFluxEntity -> {
			return topicFluxEntity.map(topic -> {
				context.getLogger().info("retrieving topic with id " + topic);
				return questionService.updateTopicById(topic);
			});
		};
	}

	@Bean
	public Consumer<Topic> deleteTopicById(ExecutionContext context) {
		return deletionRequest -> questionService.deleteTopicById(deletionRequest.getId());
	}

	@Bean
	public Function<Flux<Topic>, Flux<List<Question>>> getQuestions(ExecutionContext context) {

		return topicFluxEntity -> {
			return topicFluxEntity.map(topic -> {
				context.getLogger().info("retrieving questions of topic " + topic.getTopicTitle());
				return questionService.getAllQuestionsInTopic(topic.getId());
			});
		};

	}
}