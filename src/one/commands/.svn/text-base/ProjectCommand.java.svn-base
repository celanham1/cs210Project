/*
 * Project Command
 */
package one.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import one.main.Table;
import one.main.TableCollection;

/**
 * Projects a query list over a field list.
 */
public class ProjectCommand implements ICommand, IQueryList {

	private Pattern pattern = Pattern.compile("project(.*)over(.*);", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private String queryList, fieldList, name;
	
	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) throws ChrisException {
		Matcher matcher = pattern.matcher(input);
		if(matcher.find())
		{
			queryList = matcher.group(1).trim();
			fieldList = matcher.group(2).trim();
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
		Table a = tc.getTable(queryList);
		name = "project_"+queryList+"_over_"+fieldList;
		Table temp = new Table(name);
		temp.addDataset(a.getProjectedRows(fieldList));
		temp.addFields(a.printColNames(a.selectColFields(fieldList)));
		tc.addTable(name, temp);
	}
	
	public String getName(){
		return name;
	}

}
