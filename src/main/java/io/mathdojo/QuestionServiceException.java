package io.mathdojo;

public class QuestionServiceException extends RuntimeException {

	public static String NOT_FOUND_MESSAGE = "Object with supplied id was not found";
	public static String UNSUPPORTED_MESSAGE = "Unsupported query";
	public static String ALREADY_EXISTS_MESSAGE = "Object with supplied id already exists";
	private static final long serialVersionUID = 1447663666335989200L;
	public QuestionServiceException(String message) {
		super(message);
	};

}
