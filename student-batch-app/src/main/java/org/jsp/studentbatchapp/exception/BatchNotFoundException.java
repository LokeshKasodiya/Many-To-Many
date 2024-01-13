package org.jsp.studentbatchapp.exception;

public class BatchNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 2733182348975110592L;

	public BatchNotFoundException(String message) {
		super(message);
	}
}
