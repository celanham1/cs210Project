/*
 * Insert Command
 */
package one.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import one.main.*;
import one.values.IValue;

/**
 * Inserts values into a a table.
 */
public class InsertCommand implements ICommand{

	private Pattern pattern = Pattern.compile("insert(?:.*?)\\((.*)\\)(?:.*?)into(.*);", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private String valueList, tableName;

	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input);
		if(matcher.find())
		{
			valueList = matcher.group(1).trim();
			tableName = matcher.group(2).trim();
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() throws ChrisException{
		if (valueList.isEmpty()){
			throw new ChrisException("No values entered");
		}
		Table table = TableCollection.getTC().getTable(tableName);
		IValue[] row = table.createValues(valueList);
		table.writeRow(row, table.getNumRows());
	}
	
	
}
