/*
 * Print Command
 */
package one.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import one.main.Table;
import one.main.TableCollection;

/**
 * Prints a Table or the Table Dictionary.
 */
public class PrintCommand implements ICommand {
	
	private Pattern pattern = Pattern.compile("print (.+);", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
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
		if(tableName.toLowerCase().equals("dictionary")){
			System.out.println(TableCollection.getTC().toString());
		}
		else{
			Table table = TableCollection.getTC().getTable(tableName);
			System.out.println(table.printTable());
		}
	}

}
