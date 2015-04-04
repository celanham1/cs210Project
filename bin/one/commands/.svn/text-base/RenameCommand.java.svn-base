/*
 * Rename Command
 */
package one.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import one.main.TableCollection;

/**
 * Renames a Table
 */
public class RenameCommand implements ICommand {
	
	private Pattern pattern = Pattern.compile("rename table (.+) to (.+);", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private String oldName,newName;

	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input);
		if(matcher.find())
		{
			oldName = matcher.group(1).trim();
			newName = matcher.group(2).trim();
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() throws ChrisException{
		TableCollection.getTC().renameTable(oldName, newName);
	}

}
