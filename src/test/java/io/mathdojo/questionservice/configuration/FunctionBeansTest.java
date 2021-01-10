package io.mathdojo.questionservice.configuration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import io.mathdojo.questionservice.model.Question;
import io.mathdojo.questionservice.model.Topic;

public class FunctionBeansTest {

	private Question question;
	private FunctionBeans qf = new FunctionBeans();
	private MathDojoQuestionRepository repo = mock(MathDojoQuestionRepository.class);
	private MathDojoTopicRepository trepo = mock(MathDojoTopicRepository.class);
	private Topic topic = new Topic();
	Map<String, String> headers;
	Map<String, String> queryParams;

	@Before
	public void setUp() {
		question = new Question();
		question.setQuestionTitle("test");
		question.setDifficulty("easy");
		question.setId("test");
		qf.repository = repo;
		qf.tRepository = trepo;
		List<Question> questionList = new ArrayList<Question>();
		questionList.add(question);
		when(repo.findByQuestionTitle("test")).thenReturn(questionList);
		when(repo.findById("test")).thenReturn(Optional.of(question));
		when(repo.findAllById(new ArrayList<String>(Arrays.asList("test")))).thenReturn(questionList);
		topic.setId("test");
		topic.setName("test");
		topic.setTitle("test");
		topic.setQuestions(new ArrayList<String>(Arrays.asList("test")));
		List<Topic> topicList = new ArrayList<Topic>();
		topicList.add(topic);
		when(trepo.findByTopicTitle("test")).thenReturn(topicList);
		when(trepo.findById("test")).thenReturn(Optional.of(topic));
		headers = new HashMap<>();
		queryParams = new HashMap<>();

	}

	@Test
	public void testQuestionAndTopicFunctions() {
		Question getQuestionResult = qf.getQuestion().apply(question);
		assertEquals(getQuestionResult, question);
		qf.updateQuestion().accept(question);
		verify(repo, times(1)).save(question);
		Question getQuestionByIdResult = qf.getQuestionById().apply(question);
		assertEquals(getQuestionByIdResult, question);
		qf.deleteQuestion().accept(question);
		verify(repo, times(1)).deleteById("test");
		Topic getTopicResult = qf.getTopic().apply(topic);
		assertEquals(getTopicResult, topic);
		qf.updateTopic().accept(topic);
		verify(trepo, times(1)).save(topic);
		Topic getTopicByIdResult = qf.getTopicById().apply(topic);
		assertEquals(getTopicByIdResult, topic);
		qf.deleteTopic().accept(topic);
		verify(trepo, times(1)).deleteById("test");
		assertEquals(qf.getQuestions().apply(topic).get(0), question);

	}

}
