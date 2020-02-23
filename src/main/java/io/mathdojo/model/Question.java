package io.mathdojo.model;

public class Question {
	

    private String questionTitle;
    private String questionBody;
    private String sampleAnswer;
    private String[] hints;

    public Question() {
    }

    public Question(String questionTitle, String questionBody, String sampleAnswer, String[] hints) {
        this.questionTitle = questionTitle;
        this.questionBody = questionBody;
        this.sampleAnswer = sampleAnswer;
        this.hints = hints;
    }

	public Question(Question question) {
		 this.questionTitle = question.getQuestionTitle();
	        this.questionBody = question.getQuestionBody();
	        this.sampleAnswer = question.getSampleAnswer();
	        this.hints = question.getHints();
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



}

