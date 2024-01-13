package org.jsp.studentbatchapp.exception;

public class StudentAlreadyPresentException extends RuntimeException {
	private static final long serialVersionUID = 4017350340659439609L;
	public StudentAlreadyPresentException(String message) {
		super(message);
	}
}
