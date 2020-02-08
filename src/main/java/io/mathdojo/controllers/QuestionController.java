package io.mathdojo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.mathdojo.model.Question;

@RestController
public class QuestionController {
	
@Autowired	
QuestionRepostiory qRepo;
	
	@GetMapping("/question")
	public Question greeting() {
		return new Question("test","test","test" ,new String[] {"test","test","test"});
	}
	
	@PostMapping("/postQuestion")
	public Question greeting(@RequestBody Map<String, String> question ) {
	    String title = question.get("title");
	    String body = question.get("title");
	    String sampleAnswer = question.get("sampleAnswer");
	    String[] hints = question.get("hints").split(",") ;
	    
		return qRepo.save(new Question(title,body,sampleAnswer,hints));
	}
}
