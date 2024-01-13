package org.jsp.studentbatchapp.exception;

public class StudentNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -3978182140089147332L;

	public StudentNotFoundException(String message) {
		super(message);
	}
}
