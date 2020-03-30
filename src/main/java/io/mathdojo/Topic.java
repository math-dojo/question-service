package io.mathdojo;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "topics")
public class Topic {
	public static Topic EMPTY_DATABASE = new Topic("The database is empty", null, null, null);
	@Id
	private String id;
	private String topicTitle;
	private String name;
	private List<String> questions;

	public Topic() {

	}

	public Topic(String id, String title, String name, List<String> questions) {
		super();
		this.id = id;
		this.topicTitle = title;
		this.name = name;
		this.questions = questions;
	}

	public Topic(Map<String, String> queryParameters) {
		this(queryParameters.get("title"), queryParameters.get("title"), null, null);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTopicTitle() {
		return topicTitle;
	}

	public void setTitle(String title) {
		this.topicTitle = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getQuestions() {
		return questions;
	}

	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}

}
