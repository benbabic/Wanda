/**
 * 
 */
package fr.irit.wanda.exception;

/**
 * @author Benjamin Babic
 *
 */
public class AlreadyRegistredException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public AlreadyRegistredException() {
	}

	// Constructor that accepts a message
	public AlreadyRegistredException(String message) {
		super(message);
	}
}
