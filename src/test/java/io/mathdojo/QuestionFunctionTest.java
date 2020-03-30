package io.mathdojo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
@Ignore
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
		//Question result = qf.getQuestion();
		//assertEquals(result.getQuestionTitle(), "test");
	}

	
}
