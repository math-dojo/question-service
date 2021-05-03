package io.mathdojo;

public class QuestionServiceException extends RuntimeException {

	/**
	 * Exception that represents some error in the operations within the Question
	 * Service.
	 */

	public QuestionServiceException(String message) {
		super(message);
	}

	private static final long serialVersionUID = -4590570509941979310L;
	public static String NOT_FOUND = "Question/Topic not found";

	public static String BAD_REQUEST = "Bad request";

}
