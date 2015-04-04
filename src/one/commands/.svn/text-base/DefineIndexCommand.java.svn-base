/*
 * Define Index Command
 */
package one.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import one.main.Table;
import one.main.TableCollection;

// TODO: Auto-generated Javadoc
/**
 * Defines the index of a table.
 */
public class DefineIndexCommand implements ICommand {

	private Pattern pattern = Pattern.compile("define(?:.*?)index(?:.*?)on(.*)\\((.*)\\);", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private String tableName, fieldName;

	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input);
		if(matcher.find())
		{
			System.out.println("This is a syntactically correct define index statement");
			tableName = matcher.group(1).trim();
			fieldName = matcher.group(2).trim();
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() throws ChrisException {
		Table t = TableCollection.getTC().getTable(tableName);
		long index = t.getFieldPosition(fieldName);

	}

}
