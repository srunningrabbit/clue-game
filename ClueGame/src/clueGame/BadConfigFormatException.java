/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package clueGame;

import java.io.*;

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
