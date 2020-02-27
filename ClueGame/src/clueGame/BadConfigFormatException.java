/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package clueGame;

public class BadConfigFormatException extends Exception{

	public BadConfigFormatException() {
		super("Error: config file(s) incorrectly formatted");
	}

	public BadConfigFormatException(String message) {
		super(message);
	}
}
