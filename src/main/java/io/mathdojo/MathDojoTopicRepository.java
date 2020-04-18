package io.mathdojo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MathDojoTopicRepository extends MongoRepository<Topic, String> {

	List<Topic> findByTopicTitle(String title);


}
