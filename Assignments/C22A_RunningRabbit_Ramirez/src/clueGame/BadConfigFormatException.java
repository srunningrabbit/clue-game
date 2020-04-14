/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package clueGame;

import java.io.*;

/*
BadConfigFormatException

An exception that is thrown when config files are improper
 */

public class BadConfigFormatException extends Exception{

	public BadConfigFormatException() {
		super("Error: config file(s) incorrectly formatted");
	}

	public BadConfigFormatException(String message) throws IOException {
		super(message);
		// Write errors to log file
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("logfile.txt", true)));
		out.println(message);
		out.close();
	}
}
