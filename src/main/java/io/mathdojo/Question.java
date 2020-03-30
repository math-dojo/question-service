package io.mathdojo;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "questions")
public class Question {	
	public static Question EMPTY_DATABASE =  new Question("the database is empty", null, null, null, Integer.BYTES, null, null, null, null, null);
	//id must be kebab-case
	@Id
	private String id;
    private String questionTitle;
    private String questionBody;
    private String sampleAnswer;
    private int successRate;
    private String difficulty;
    private String[] hints;
    private String parentTopicTitle;
    private String[] questionAnswerOptions;
    private String answer;

    public Question() {
    }

	public Question(String id, String questionTitle, String questionBody, String sampleAnswer, int successRate,
			String difficulty, String[] hints, String parentTopicTitle, String[] questionAnswerOptions, String answer) {
		super();
		this.id = id;
		this.questionTitle = questionTitle;
		this.questionBody = questionBody;
		this.sampleAnswer = sampleAnswer;
		this.successRate = successRate;
		this.difficulty = difficulty;
		this.hints = hints;
		this.parentTopicTitle = parentTopicTitle;
		this.questionAnswerOptions = questionAnswerOptions;
		this.answer = answer;
	}

	public Question(Map<String, String> queryParameters) {
		this("the database is empty", queryParameters.get("title"), null, null, Integer.BYTES, queryParameters.get("difficulty"), null, null, null, null);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getQuestionBody() {
		return questionBody;
	}

	public void setQuestionBody(String questionBody) {
		this.questionBody = questionBody;
	}

	public String getSampleAnswer() {
		return sampleAnswer;
	}

	public void setSampleAnswer(String sampleAnswer) {
		this.sampleAnswer = sampleAnswer;
	}

	public int getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(int successRate) {
		this.successRate = successRate;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String[] getHints() {
		return hints;
	}

	public void setHints(String[] hints) {
		this.hints = hints;
	}

	public String getParentTopicTitle() {
		return parentTopicTitle;
	}

	public void setParentTopicTitle(String parentTopicTitle) {
		this.parentTopicTitle = parentTopicTitle;
	}

	public String[] getQuestionAnswerOptions() {
		return questionAnswerOptions;
	}

	public void setQuestionAnswerOptions(String[] questionAnswerOptions) {
		this.questionAnswerOptions = questionAnswerOptions;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}



    
}
