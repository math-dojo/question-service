package io.mathdojo.questionservice.configuration;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.mathdojo.questionservice.model.Question;

@Repository
public interface MathDojoQuestionRepository extends MongoRepository<Question, String> {

	List<Question> findByQuestionTitle(String questionTitle);


}
