/*
 * Select Command
 */
package one.commands;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import one.main.Table;
import one.main.TableCollection;


// TODO: Auto-generated Javadoc
/**
 * Selects a query list.
 */
public class SelectCommand implements ICommand, IQueryList{

	private Pattern pattern = Pattern.compile("select (.*?)(?: where (.*))?;", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private String queryList, expression, name;
	
	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) throws ChrisException {
		Matcher matcher = pattern.matcher(input);
		if(matcher.find())
		{
			queryList = matcher.group(1);
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
		TableCollection tc = TableCollection.getTC();
		Table table = tc.getTable(queryList);
		if(expression == null){
			name = queryList;
		}
		else{
			Pattern where = Pattern.compile("([^<>!=]+)([<>!=]{1,2}+)([^<>!=]+)", Pattern.CASE_INSENSITIVE);
			Matcher matcher = where.matcher(expression);
			if(matcher.find()){
				ArrayList<Integer> selectedRows = table.getSelectRows(matcher.group(1).trim(), matcher.group(2).trim(),matcher.group(3).trim());
				name = "select_"+queryList;
				Table temp = new Table(name);
				temp.addFields(table.getFieldNames());
				temp.addDataset(table.selectRows(selectedRows));
				tc.addTable(name, temp);
			}
		}
		
	}
	
	public String getName(){
		return name;
	}
	
}




