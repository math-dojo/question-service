package io.mathdojo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
@RunWith(MockitoJUnitRunner.class)
public class QuestionFunctionTest {

	private Question testQuestion1 = new Question("test-question-1", "test question 1", "test", "test", 50, "easy",  new String[]{""}, "test", new String[]{""}, "test");
	private Question testQuestion2 = new Question("test-question-2", "test question 2", "test", "test", 40, "easy",  new String[]{""}, "test", new String[]{""}, "test");
	private Question testQuestion3 = new Question("test-question-3", "test question 3", "test", "test", 30, "hard",  new String[]{""}, "test", new String[]{""}, "test");
	private List<String> questionList = new ArrayList<>(Arrays.asList("test-question-1", "test-question-2", "test-question-3"));
	private Topic testTopic = new Topic("test-topic", "test topic", "test", questionList);

	
	@Mock
	private MathDojoQuestionRepository qRepo;
	@Mock
	private MathDojoTopicRepository tRepo;
	@InjectMocks
	private QuestionFunction qf ;
	
	Map<String, String> headers;
	Map<String, String> queryParams;

	@Before
	public void setUp() {
		Mockito.when(qRepo.save(Mockito.any(Question.class))).thenAnswer(new Answer<Question>() {
            public Question answer(InvocationOnMock invocation) {
                return (Question) invocation.getArguments()[0];
            }
        });
		Mockito.when(tRepo.save(Mockito.any(Topic.class))).thenAnswer(new Answer<Topic>() {
            public Topic answer(InvocationOnMock invocation) {
                return (Topic) invocation.getArguments()[0];
            }
        });
		Mockito.when(tRepo.findById("test-topic")).thenReturn(Optional.of(testTopic));
		Mockito.when(tRepo.findByTopicTitle("test topic")).thenReturn(new ArrayList<Topic>(Arrays.asList(testTopic)));
		Mockito.when(qRepo.findByQuestionTitle("test question 1")).thenReturn(new ArrayList<Question>(Arrays.asList(testQuestion1)));
		
		Mockito.lenient().when(qRepo.findById("test-question-1")).thenReturn(Optional.of(testQuestion1));
		Mockito.lenient().when(qRepo.findById("test-question-2")).thenReturn(Optional.of(testQuestion2));
		Mockito.lenient().when(qRepo.findById("test-question-3")).thenReturn(Optional.of(testQuestion3));


	}

	@Test
	public void testCreateQuestion(){
		Function<Question, Question> createQuestion =  qf.createQuestion();
		 assertDoesNotThrow(() -> createQuestion.apply(testQuestion1));
		 verify(qRepo, times(1)).save(testQuestion1);
	}
	@Test	
	public void testGetQuestionReturnsQuestion(){
		 Function<Question, Question> getQuestion =  qf.getQuestion();
		assertEquals(getQuestion.apply(testQuestion1), testQuestion1);	
	}
	@Test	
	public void testGetQuestionByIdReturnsQuestion(){
		 Function<Question, Question> getQuestionById =  qf.getQuestionById();
		assertEquals(getQuestionById.apply(testQuestion1), testQuestion1);	
	}
	@Test	
	public void testUpdateQuestion(){
		 Question newTestQuestion1 = new Question("test-question-1", "test question 1", "test", "test", 50, "hard",  new String[]{""}, "test", new String[]{""}, "test");
		 Function<Question, Question> updateQuestion =  qf.updateQuestion();
		 updateQuestion.apply(newTestQuestion1);
		 verify(qRepo, times(1)).save(newTestQuestion1);
		 
	}
	
	@Test
	public void testDeleteQuestion(){
		 Consumer<Question> deleteQuestion =  qf.deleteQuestion();
		 assertDoesNotThrow(() -> deleteQuestion.accept(testQuestion1));
		 verify(qRepo, times(1)).deleteById("test-question-1");
	}
	
	@Test
	public void testCreateTopic(){
		Function<Topic, Topic> createTopic =  qf.createTopic();
		 assertEquals(createTopic.apply(testTopic), testTopic);
		 verify(tRepo, times(1)).save(testTopic);
	}
	
	@Test	
	public void testGetTopicReturnsTopic(){
		 Function<Topic, Topic> getTopic =  qf.getTopic();
		assertEquals(getTopic.apply(testTopic), testTopic);	
	}
	@Test	
	public void testGetTopicByIdReturnsTopic(){
		 Function<Topic, Topic> getTopicById =  qf.getTopicById();
		assertEquals(getTopicById.apply(testTopic), testTopic);	
	}
	@Test	
	public void testUpdateTopic(){
		 Topic newTestTopic1 = new Topic("test-topic", "test topic", "testUpdate", new ArrayList<>(Arrays.asList("test-question-1", "test-question-2", "test-question-3")));
		 Function<Topic, Topic> updateTopic =  qf.updateTopic();
		 updateTopic.apply(newTestTopic1);
		 verify(tRepo, times(1)).save(newTestTopic1);
		 
	}
	
	@Test
	public void testDeleteTopic(){
		 Consumer<Topic> deleteTopic =  qf.deleteTopic();
		 assertDoesNotThrow(() -> deleteTopic.accept(testTopic));
		 verify(tRepo, times(1)).deleteById("test-topic");
	}
	
	@Test
	public void testGetQuestions(){
		Function<Topic, List<Question>> getQuestions =  qf.getQuestions();
		getQuestions.apply(testTopic);
		 verify(qRepo, times(1)).findAllById(questionList);
	}
	@Test
	public void testGetQuestionsExceptionThrown(){
		Function<Topic, List<Question>> getQuestions =  qf.getQuestions();
		assertThrows(QuestionServiceException.class, () -> getQuestions.apply(new Topic()), "topic not found");
	}
		


}
