package fr.irit.wanda.exception;

public class NotAllowedToProceedException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotAllowedToProceedException() {
	}

	// Constructor that accepts a message
	public NotAllowedToProceedException(String message) {
		super(message);
	}

}
