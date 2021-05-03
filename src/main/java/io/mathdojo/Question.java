package io.mathdojo;

import java.util.Arrays;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "questions")
public class Question {	
	public static Question EMPTY_DATABASE =  new Question("the-database-is-empty", null, null, null, 0, null, null, null, null, null);
	//id must be kebab-case
	@Id
	private String id;
    private String questionTitle;
    private String questionBody;
    private String sampleAnswer;
    private Integer successRate;
    private String difficulty;
    private String[] hints;
    private String parentTopicTitle;
    private String[] questionAnswerOptions;
    private String answer;

    public Question() {
    }

	public Question(String id, String questionTitle, String questionBody, String sampleAnswer, Integer successRate,
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
		this("the database is empty", queryParameters.get("title"), null, null, null, queryParameters.get("difficulty"), null, null, null, null);
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

	public Integer getSuccessRate() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());	
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (difficulty == null) {
			if (other.difficulty != null)
				return false;
		} else if (!difficulty.equals(other.difficulty))
			return false;
		if (!Arrays.equals(hints, other.hints))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (parentTopicTitle == null) {
			if (other.parentTopicTitle != null)
				return false;
		} else if (!parentTopicTitle.equals(other.parentTopicTitle))
			return false;
		if (!Arrays.equals(questionAnswerOptions, other.questionAnswerOptions))
			return false;
		if (questionBody == null) {
			if (other.questionBody != null)
				return false;
		} else if (!questionBody.equals(other.questionBody))
			return false;
		if (questionTitle == null) {
			if (other.questionTitle != null)
				return false;
		} else if (!questionTitle.equals(other.questionTitle))
			return false;
		if (sampleAnswer == null) {
			if (other.sampleAnswer != null)
				return false;
		} else if (!sampleAnswer.equals(other.sampleAnswer))
			return false;
		if (successRate != other.successRate)
			return false;
		return true;
	}

	public boolean isValid() {
		//object validation rules to be decided
		return true;
	}



    
}
