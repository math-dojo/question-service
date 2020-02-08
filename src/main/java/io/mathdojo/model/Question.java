package io.mathdojo.model;

public class Question {
	
	private final String title;
	private final String body;
	private final String sampleAnswer;
	private final String[] hints;
	public Question(String title, String body, String sampleAnswer, String[] hints) {
		super();
		this.title = title;
		this.body = body;
		this.sampleAnswer = sampleAnswer;
		this.hints = hints;
	}
	public String getTitle() {
		return title;
	}
	public String getBody() {
		return body;
	}
	public String getSampleAnswer() {
		return sampleAnswer;
	}
	public String[] getHints() {
		return hints;
	}

}
