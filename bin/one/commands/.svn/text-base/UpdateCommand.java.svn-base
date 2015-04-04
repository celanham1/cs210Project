/*
 * Update Command
 */
package one.commands;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import one.main.Table;
import one.main.TableCollection;

/**
 * Updates a table.
 */
public class UpdateCommand implements ICommand {

	private Pattern pattern = Pattern.compile("update(.*?)set(.*?)=(.*?)(?:where(.*))?;", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private String tableName, fieldName, value, expression;
	
	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input);
		if(matcher.find())
		{
			tableName = matcher.group(1).trim();
			fieldName = matcher.group(2).trim();
			value = matcher.group(3).trim();
			expression = matcher.group(4);
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
			for (int r = 0; r < table.getNumRows(); r++){
				table.updateValue(r,fieldName,value);
			}
		}
		else{
			Pattern where = Pattern.compile("([^<>!=]+)([<>!=]{1,2}+)([^<>!=]+)", Pattern.CASE_INSENSITIVE);
			Matcher matcher = where.matcher(expression);
			if(matcher.find()){
				ArrayList<Integer> selectedRows = table.getSelectRows(matcher.group(1).trim(), matcher.group(2).trim(),matcher.group(3).trim());
				for (Integer i : selectedRows){
					table.updateValue(i,fieldName,value);
				}
			}
		}

	}

}
