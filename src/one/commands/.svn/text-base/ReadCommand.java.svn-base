/*
 * Read Command
 */
package one.commands;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import one.main.*;

// TODO: Auto-generated Javadoc
/**
 * Reads a file and executes the commands in it.
 */
public class ReadCommand implements ICommand {

	private Pattern pattern = Pattern.compile("read \'(.*)\';", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private String filename;
	
	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input);
		if(matcher.find())
		{
			filename = matcher.group(1);
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() throws ChrisException {
		try{
			Scanner infile = new Scanner(new FileReader(filename));
			Driver drive = new Driver();
			while(infile.hasNext())
			{
				drive.match(infile);
			}
		} catch (FileNotFoundException e){
			throw new ChrisException("File not found. Enter a new command",e);
		}
	}
}
