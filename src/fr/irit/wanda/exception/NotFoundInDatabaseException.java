package fr.irit.wanda.exception;

public class NotFoundInDatabaseException extends Exception {

	private static final long serialVersionUID = 1L;

	// Parameterless Constructor
	public NotFoundInDatabaseException() {
	}

	// Constructor that accepts a message
	public NotFoundInDatabaseException(String message) {
		super(message);
	}

}
