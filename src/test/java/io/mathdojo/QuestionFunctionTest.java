package io.mathdojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

public class QuestionFunctionTest {

	@Test
	public void test() {
		Question question = mock(Question.class);
		when(question.getQuestionTitle()).thenReturn("test");
		QuestionFunction qf = new QuestionFunction();
		qf.repository = mock(MathDojoQuestionRepository.class);
		List<Question> questionList = new ArrayList<Question>();
		questionList.add(question);
		when(qf.repository.findAll()).thenReturn(questionList);
		Question result = qf.getQuestion().apply(new GetObject());
		assertEquals(result.getQuestionTitle(), "test");
	}

	@Test
	@Ignore
	public void start() throws Exception {
		AzureSpringBootRequestHandler<GetObject, Question> handler = new AzureSpringBootRequestHandler<>(
				QuestionFunction.class);
		Question result = handler.handleRequest(new GetObject(), null);
		handler.close();
		assertFalse(result.isSolved());
	}
}
