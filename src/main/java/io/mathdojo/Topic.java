package io.mathdojo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "topics")
public class Topic {
	@Id
	private String id;
	private String title;
	private String body;
	private List<String> questions;

	public Topic() {

	}

	public Topic(String id, String title, String body, List<String> questions) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.questions = questions;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTopicTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return body;
	}

	public void setName(String name) {
		this.body = name;
	}

	public List<String> getQuestions() {
		return questions;
	}

	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}
	
	public Topic updateNonNullAttributes(Topic topic) {
		Topic newTopic = new Topic(topic.getId(),
				topic.getTopicTitle() != null ? topic.getTopicTitle() : this.getTopicTitle(),
				topic.getName() != null ? topic.getName() : this.getName(),
				topic.getQuestions() != null ? topic.getQuestions() : this.getQuestions());
		return newTopic;
				
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
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
