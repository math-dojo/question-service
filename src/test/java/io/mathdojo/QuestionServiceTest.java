package io.mathdojo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {
	private static final String PRECONDITIONED_KNOWN_QUESTION = "aKnownQuestion";
	private static final String PRECONDITIONED_KNOWN_TOPIC = "aKnownTopic";

	@Mock
	private MathDojoQuestionRepository qRepo;
	@Mock
	private MathDojoTopicRepository tRepo;
	@InjectMocks
	private QuestionService questionService;

	@SuppressWarnings("unchecked")
	@BeforeEach
	public void setUp() {
		Question knownQuestion = new Question(PRECONDITIONED_KNOWN_QUESTION, PRECONDITIONED_KNOWN_QUESTION, null, null,
				null, "Easy", null, null, null, null);
		Iterable<Question> ite = Arrays.asList(knownQuestion);
		Topic knownTopic = new Topic(PRECONDITIONED_KNOWN_TOPIC, PRECONDITIONED_KNOWN_TOPIC, PRECONDITIONED_KNOWN_TOPIC,
				new ArrayList<String>(Arrays.asList(PRECONDITIONED_KNOWN_QUESTION)));
		Mockito.lenient().when(qRepo.save(Mockito.any(Question.class))).thenAnswer(new Answer<Question>() {
			public Question answer(InvocationOnMock invocation) {
				return (Question) invocation.getArguments()[0];
			}
		});
		Mockito.lenient().when(tRepo.save(Mockito.any(Topic.class))).thenAnswer(new Answer<Topic>() {
			public Topic answer(InvocationOnMock invocation) {
				return (Topic) invocation.getArguments()[0];
			}
		});

		Mockito.lenient().when(qRepo.existsById(PRECONDITIONED_KNOWN_QUESTION)).thenReturn(true);
		Mockito.lenient().when(tRepo.existsById(PRECONDITIONED_KNOWN_TOPIC)).thenReturn(true);
		Mockito.lenient().when(tRepo.findByTopicTitle(PRECONDITIONED_KNOWN_TOPIC))
				.thenReturn(Arrays.asList(knownTopic));
		Mockito.lenient().when(qRepo.findByQuestionTitle(PRECONDITIONED_KNOWN_QUESTION))
				.thenReturn(Arrays.asList(knownQuestion));
		Mockito.lenient().when(qRepo.findByDifficulty("Easy")).thenReturn(Arrays.asList(knownQuestion));
		Mockito.lenient().when(qRepo.findById(PRECONDITIONED_KNOWN_QUESTION)).thenReturn(Optional.of(knownQuestion));
		Mockito.lenient().when(tRepo.findById(PRECONDITIONED_KNOWN_TOPIC))
				.thenReturn(Optional.of(new Topic(PRECONDITIONED_KNOWN_TOPIC, PRECONDITIONED_KNOWN_TOPIC,
						PRECONDITIONED_KNOWN_TOPIC, Arrays.asList(PRECONDITIONED_KNOWN_QUESTION))));

	}

	@Test
	public void testGetQuestionById() {
		Question question = questionService.getQuestionById(PRECONDITIONED_KNOWN_QUESTION);
		assertEquals(question.getId(), PRECONDITIONED_KNOWN_QUESTION);

		RuntimeException exception = assertThrows(QuestionServiceException.class, () -> {
			questionService.getQuestionById(null);
		});

		String exceptionMessage = exception.getMessage();
		assertEquals(QuestionServiceException.NOT_FOUND, exceptionMessage);

	}

	@Test
	public void testGetTopicById() {
		Topic topic = questionService.getTopicById(PRECONDITIONED_KNOWN_TOPIC);
		assertEquals(topic.getId(), PRECONDITIONED_KNOWN_TOPIC);
		RuntimeException exception = assertThrows(QuestionServiceException.class, () -> {
			questionService.getTopicById(null);
		});

		String exceptionMessage = exception.getMessage();
		assertEquals(QuestionServiceException.NOT_FOUND, exceptionMessage);
	}

	@Test
	public void testUpdateQuestion() {
		Question question = questionService.getQuestionById(PRECONDITIONED_KNOWN_QUESTION);
		question.setAnswer("testAnswer");
		Question updated = questionService.updateQuestionById(question);
		assertEquals(updated.getAnswer(), "testAnswer");

		RuntimeException exception = assertThrows(QuestionServiceException.class, () -> {
			questionService.updateQuestionById(new Question());
		});

		String exceptionMessage = exception.getMessage();
		assertEquals(QuestionServiceException.NOT_FOUND, exceptionMessage);

	}

	@Test
	public void testUpdateTopic() {
		Topic topic = questionService.getTopicById(PRECONDITIONED_KNOWN_TOPIC);
		topic.setName("testName");
		Topic updated = questionService.updateTopicById(topic);
		assertEquals(updated.getName(), "testName");

		RuntimeException exception = assertThrows(QuestionServiceException.class, () -> {
			questionService.updateTopicById(new Topic());
		});

		String exceptionMessage = exception.getMessage();
		assertEquals(QuestionServiceException.NOT_FOUND, exceptionMessage);

	}

	@Test
	public void testDeleteQuestionById() {

		RuntimeException exception = assertThrows(QuestionServiceException.class, () -> {
			questionService.deleteQuestionById(null);
			;
		});

		String exceptionMessage = exception.getMessage();
		assertEquals(QuestionServiceException.NOT_FOUND, exceptionMessage);

	}

	@Test
	public void testDeleteTopicById() {
		RuntimeException exception = assertThrows(QuestionServiceException.class, () -> {
			questionService.deleteTopicById(null);
		});

		String exceptionMessage = exception.getMessage();
		assertEquals(QuestionServiceException.NOT_FOUND, exceptionMessage);

	}

	@Test
	public void testCreateQuestion() {
		Question question = questionService.getQuestionById(PRECONDITIONED_KNOWN_QUESTION);

		RuntimeException exception = assertThrows(QuestionServiceException.class, () -> {
			questionService.createQuestion(question);
		});

		String exceptionMessage = exception.getMessage();
		assertEquals(QuestionServiceException.BAD_REQUEST, exceptionMessage);

	}

	@Test
	public void testCreateTopic() {
		Topic topic = questionService.getTopicById(PRECONDITIONED_KNOWN_TOPIC);

		RuntimeException exception = assertThrows(QuestionServiceException.class, () -> {
			questionService.createTopic(topic);
		});

		String exceptionMessage = exception.getMessage();
		assertEquals(QuestionServiceException.BAD_REQUEST, exceptionMessage);
	}

	@Test
	public void testGetAllQuestionsInTopic() {

		RuntimeException exception = assertThrows(QuestionServiceException.class, () -> {
			questionService.getAllQuestionsInTopic(null);
		});

		String exceptionMessage = exception.getMessage();
		assertEquals(QuestionServiceException.NOT_FOUND, exceptionMessage);
	}

	@Test
	public void testGetTopicsByTitle() {

		List<Topic> topics = questionService.findTopicsByTitle(PRECONDITIONED_KNOWN_TOPIC);
		assertEquals(topics.get(0).getId(), PRECONDITIONED_KNOWN_TOPIC);

	}

	@Test
	public void testGetQuestionsByTitleAndDifficulty() {

		List<Question> questions = questionService.findQuestionsByTitleAndDifficulty(PRECONDITIONED_KNOWN_QUESTION,
				"Easy");
		assertEquals(questions.size(), 1);
		assertEquals(questions.get(0).getId(), PRECONDITIONED_KNOWN_QUESTION);

	}
}
