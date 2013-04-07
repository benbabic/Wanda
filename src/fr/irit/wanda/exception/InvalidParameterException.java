package fr.irit.wanda.exception;

public class InvalidParameterException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidParameterException() {
	}

	// Constructor that accepts a message
	public InvalidParameterException(String message) {
		super(message);
	}
}
