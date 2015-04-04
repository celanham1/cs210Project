/*
 * Exit Command
 */
package one.commands;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import one.main.*;


// TODO: Auto-generated Javadoc

/**
 * The Exit command 
 */
public class ExitCommand implements ICommand {

	private Pattern pattern = Pattern.compile("exit;", Pattern.CASE_INSENSITIVE);

	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input);
		if(matcher.find())
		{
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() throws ChrisException {
		String file = "database.xml";
		try {
			new XMLWriter().write(file);
		} catch (FileNotFoundException e) {
			throw new ChrisException("File not found",e);
		}
		System.exit(0);
	}
	
	

}
