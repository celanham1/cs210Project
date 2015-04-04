/*
 * Delete Command
 */
package one.commands;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import one.main.Table;
import one.main.TableCollection;

/**
 * The Delete command 
 */
public class DeleteCommand implements ICommand {
	
	private Pattern pattern = Pattern.compile("delete (.*?)(?: where (.*))?;", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private String tableName, expression;
	
	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input);
		if(matcher.find())
		{
			tableName = matcher.group(1).trim();
			expression = matcher.group(2);
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() throws ChrisException {
		Table table = TableCollection.getTC().getTable(tableName);
		if(expression == null){
			table.deleteAllRows();
		}
		else{
			Pattern where = Pattern.compile("([^<>!=]+)([<>!=]{1,2}+)([^<>!=]+)", Pattern.CASE_INSENSITIVE);
			Matcher matcher = where.matcher(expression);
			if(matcher.find()){
				ArrayList<Integer> selectedRows = table.getSelectRows(matcher.group(1).trim(), matcher.group(2).trim(),matcher.group(3).trim());
				table.deleteSelectRows(selectedRows);
			}
		}
	}

}
