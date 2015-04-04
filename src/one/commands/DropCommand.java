/*
 * Drop Command
 */
package one.commands;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import one.main.TableCollection;

/**
 * Drops a Table from the TableCollection
 */
public class DropCommand implements ICommand {
	
	private Pattern pattern = Pattern.compile("drop table (.*);", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private String tableName;
	
	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input);
		if(matcher.find())
		{
			tableName = matcher.group(1).trim();
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() throws ChrisException{
		if (tableName.isEmpty()){
			throw new ChrisException("No table name entered");
		}
		TableCollection.getTC().dropTable(tableName);
		File data = new File("data"+File.separator+tableName+".dat");
		if(data.exists()) data.delete();
	}
}
