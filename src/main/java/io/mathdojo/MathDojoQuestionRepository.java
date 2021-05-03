package io.mathdojo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MathDojoQuestionRepository extends MongoRepository<Question, String> {

	List<Question> findByQuestionTitle(String questionTitle);
	
	List<Question> findByDifficulty(String difficulty);


}
