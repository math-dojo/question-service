package io.mathdojo.questionservice.configuration;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.mathdojo.questionservice.model.Topic;

@Repository
public interface MathDojoTopicRepository extends MongoRepository<Topic, String> {

	List<Topic> findByTopicTitle(String title);


}
