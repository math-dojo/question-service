package io.mathdojo.model;

import org.springframework.stereotype.Repository;

import com.microsoft.azure.spring.data.cosmosdb.repository.ReactiveCosmosRepository;

@Repository
public interface QuestionRepository extends ReactiveCosmosRepository<Question, String> {


}
