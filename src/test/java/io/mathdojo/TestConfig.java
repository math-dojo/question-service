package io.mathdojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@TestConfiguration
public class TestConfig {
	private Map<String, Question> createdQuestions = new HashMap<>();
	private Map<String, Topic> createdTopics = new HashMap<>();
	public static Question PRECONFIGURED_QUESTION = new Question("test-question", "test question", "test", "test", 50, "easy",
			new String[] { "" }, "test", new String[] { "" }, "test");
	public static Topic PRECONFIGURED_TOPIC = new Topic("test-topic", "test topic", "test",new ArrayList<>());

	@Bean
	MathDojoQuestionRepository qRepository() {
		createdQuestions.put("test-question", PRECONFIGURED_QUESTION);

		MathDojoQuestionRepository questionRepo = new MathDojoQuestionRepository() {

			@Override
			public <S extends Question> Optional<S> findOne(Example<S> example) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Question> Page<S> findAll(Example<S> example, Pageable pageable) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Question> boolean exists(Example<S> example) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public <S extends Question> long count(Example<S> example) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public <S extends Question> S save(S entity) {
				createdQuestions.put(entity.getId(), entity);
				return entity;
			}

			@Override
			public Optional<Question> findById(String id) {
				// TODO Auto-generated method stub
				return Optional.of(createdQuestions.get(id));
			}

			@Override
			public Iterable<Question> findAllById(Iterable<String> ids) {
				List<Question> retVal = new ArrayList<>();
				for(String s : ids) {
					if(createdQuestions.containsKey(s)) {
						retVal.add(createdQuestions.get(s));
					}
				}
				return retVal;
			}

			@Override
			public boolean existsById(String id) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void deleteById(String id) {
				createdQuestions.remove(id);

			}

			@Override
			public void deleteAll(Iterable<? extends Question> entities) {
				// TODO Auto-generated method stub

			}

			@Override
			public void deleteAll() {
				// TODO Auto-generated method stub

			}

			@Override
			public void delete(Question entity) {
				// TODO Auto-generated method stub

			}

			@Override
			public long count() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Page<Question> findAll(Pageable pageable) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Question> List<S> saveAll(Iterable<S> entities) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Question> List<S> insert(Iterable<S> entities) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Question> S insert(S entity) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Question> List<S> findAll(Example<S> example, Sort sort) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Question> List<S> findAll(Example<S> example) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<Question> findAll(Sort sort) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<Question> findAll() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<Question> findByQuestionTitle(String questionTitle) {
				List<Question> retVal = new ArrayList<>();
				for (String s : createdQuestions.keySet()) {
					if (createdQuestions.get(s).getQuestionTitle().equals(questionTitle)) {
						retVal.add(createdQuestions.get(s));
					}
				}
				return retVal;
			}
		};
		return questionRepo;
	}

	@Bean
	MathDojoTopicRepository tRepository() {
		createdTopics.put("test-topic", PRECONFIGURED_TOPIC);

		MathDojoTopicRepository topicRepo = new MathDojoTopicRepository() {

			@Override
			public <S extends Topic> Optional<S> findOne(Example<S> example) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Topic> Page<S> findAll(Example<S> example, Pageable pageable) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Topic> boolean exists(Example<S> example) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public <S extends Topic> long count(Example<S> example) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public <S extends Topic> S save(S entity) {
				createdTopics.put(entity.getId(), entity);
				return entity;
			}

			@Override
			public Optional<Topic> findById(String id) {
				return Optional.of(createdTopics.get(id));
			}

			@Override
			public Iterable<Topic> findAllById(Iterable<String> ids) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean existsById(String id) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void deleteById(String id) {
				createdTopics.remove(id);

			}

			@Override
			public void deleteAll(Iterable<? extends Topic> entities) {
				// TODO Auto-generated method stub

			}

			@Override
			public void deleteAll() {
				// TODO Auto-generated method stub

			}

			@Override
			public void delete(Topic entity) {
				

			}

			@Override
			public long count() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Page<Topic> findAll(Pageable pageable) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Topic> List<S> saveAll(Iterable<S> entities) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Topic> List<S> insert(Iterable<S> entities) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Topic> S insert(S entity) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Topic> List<S> findAll(Example<S> example, Sort sort) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Topic> List<S> findAll(Example<S> example) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<Topic> findAll(Sort sort) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<Topic> findAll() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<Topic> findByTopicTitle(String title) {
				List<Topic> retVal = new ArrayList<>();
				for (String s : createdTopics.keySet()) {
					if (title.equals(createdTopics.get(s).getTopicTitle())) {
						retVal.add(createdTopics.get(s));
					}
				}
				return retVal;
			}
			
		};
		return topicRepo;
	}
}
