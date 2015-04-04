/*
 * Command Interface
 */
package one.commands;

import java.io.FileNotFoundException;

/**
 * The Interface ICommand.
 */
public interface ICommand {

	/**
	 * Compares a string with a command statement and
	 * determines if it matches or not.
	 *
	 * @param input the entered command
	 * @return true, if successful
	 * @throws ChrisException 
	 */
	public boolean matches (String input) throws ChrisException;
	
	/**
	 * Execute the command.
	 * @throws ChrisException 
	 * @throws FileNotFoundException 
	 */
	public void execute() throws ChrisException;
}
