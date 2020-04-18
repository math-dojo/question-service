package io.mathdojo;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "topics")
public class Topic {
	public static Topic EMPTY_DATABASE = new Topic("The-database-is-empty", null, null, null);
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
		Topic other = (Topic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		if (topicTitle == null) {
			if (other.topicTitle != null)
				return false;
		} else if (!topicTitle.equals(other.topicTitle))
			return false;
		return true;
	}

}
