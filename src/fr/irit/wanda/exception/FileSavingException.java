package fr.irit.wanda.exception;

public class FileSavingException extends Exception {

	private static final long serialVersionUID = 1L;

	public FileSavingException() {
	}

	// Constructor that accepts a message
	public FileSavingException(String message) {
		super(message);
	}
}
