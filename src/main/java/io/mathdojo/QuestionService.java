package io.mathdojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

public class QuestionService {


	@Autowired
	private MathDojoQuestionRepository qRepo;

	@Autowired
	private MathDojoTopicRepository tRepo;

	public QuestionService() {
	}

	public Question getQuestionById(String id) throws QuestionServiceException {
		if (!questionExists(id)) {
			throw new QuestionServiceException(QuestionServiceException.NOT_FOUND);
		}
		return qRepo.findById(id).get();
	}

	public Topic getTopicById(String id) throws QuestionServiceException {
		if (!topicExists(id)) {
			throw new QuestionServiceException(QuestionServiceException.NOT_FOUND);
		}
		return tRepo.findById(id).get();
	}

	public Question updateQuestionById(Question question) throws QuestionServiceException {
		if (!questionExists(question.getId())) {
			throw new QuestionServiceException(QuestionServiceException.NOT_FOUND);
		}
		Question oldQuestion = qRepo.findById(question.getId()).get();

		Question newQuestion = new Question(question.getId(),
				question.getQuestionTitle() != null ? question.getQuestionTitle() : oldQuestion.getQuestionTitle(),
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

		return qRepo.save(newQuestion);

	}

	public Topic updateTopicById(Topic topic) throws QuestionServiceException {
		if (!topicExists(topic.getId())) {
			throw new QuestionServiceException(QuestionServiceException.NOT_FOUND);
		}
		Topic oldTopic = tRepo.findById(topic.getId()).get();

		Topic newTopic = new Topic(topic.getId(),
				topic.getTopicTitle() != null ? topic.getTopicTitle() : oldTopic.getTopicTitle(),
				topic.getName() != null ? topic.getName() : oldTopic.getName(),
				topic.getQuestions() != null ? topic.getQuestions() : oldTopic.getQuestions());
		return tRepo.save(newTopic);

	}

	public void deleteQuestionById(String id) throws QuestionServiceException {
		if (!questionExists(id)) {
			throw new QuestionServiceException(QuestionServiceException.NOT_FOUND);
		}
		qRepo.deleteById(id);
	}

	public void deleteTopicById(String id) throws QuestionServiceException {
		if (!topicExists(id)) {
			throw new QuestionServiceException(QuestionServiceException.NOT_FOUND);
		}
		tRepo.deleteById(id);
	}

	public Question createQuestion(Question question) throws QuestionServiceException {
		if (questionExists(question.getId()) || !question.isValid()) {
			throw new QuestionServiceException(QuestionServiceException.BAD_REQUEST);
		}
		return qRepo.save(question);
	}

	public Topic createTopic(Topic topic) throws QuestionServiceException {
		if (topicExists(topic.getId()) || !topic.isValid()) {
			throw new QuestionServiceException(QuestionServiceException.BAD_REQUEST);
		}
		return tRepo.save(topic);
	}

	public List<Question> findQuestionsByTitle(String title) {

		return qRepo.findByQuestionTitle(title);
	}

	public List<Question> getAllQuestionsInTopic(String id) throws QuestionServiceException {
		if (!topicExists(id)) {
			throw new QuestionServiceException(QuestionServiceException.NOT_FOUND);
		}
		List<Question> retVal = new ArrayList<>();
		qRepo.findAllById(tRepo.findById(id).get().getQuestions()).forEach(retVal::add);
		return retVal;
	}

	public List<Topic> findTopicsByTitle(String title) {

		return tRepo.findByTopicTitle(title);
	}

	public List<Question> findQuestionsByDifficulty(String difficulty) {

		return qRepo.findByQuestionTitle(difficulty);
	}

	private boolean questionExists(String id) {

		return qRepo.existsById(id);
	}

	private boolean topicExists(String id) {

		return tRepo.existsById(id);
	}

	public List<Question> findQuestionsByTitleAndDifficulty(String questionTitle, String difficulty) {
		Set<Question> retVal = new HashSet<>();
		retVal.addAll(findQuestionsByDifficulty(difficulty));
		retVal.addAll(findQuestionsByTitle(questionTitle));		
		return new ArrayList<>(retVal);
	}

}
