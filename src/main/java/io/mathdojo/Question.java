package io.mathdojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "questions")
public class Question {
	
	@Id
    private String questionTitle;
    private String questionBody;
    private String sampleAnswer;
    private String[] hints;
    private int successRate;
    private String difficulty;
    private boolean solved;

    public Question() {
    }

	public Question(String questionTitle, String questionBody, String sampleAnswer, String[] hints, int successRate,
			String difficulty, boolean solved) {
		super();
		this.questionTitle = questionTitle;
		this.questionBody = questionBody;
		this.sampleAnswer = sampleAnswer;
		this.hints = hints;
		this.successRate = successRate;
		this.difficulty = difficulty;
		this.solved = solved;
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

	public String[] getHints() {
		return hints;
	}

	public void setHints(String[] hints) {
		this.hints = hints;
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

	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

    
}
