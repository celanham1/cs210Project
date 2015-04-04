/*
 * Intersect Command
 */
package one.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import one.main.Table;
import one.main.TableCollection;


/**
 * Intersects two query lists.
 */
public class IntersectCommand implements ICommand, IQueryList {

	private Pattern pattern = Pattern.compile("intersect (.*) and (.*);", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private String queryList1, queryList2, name;

	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) throws ChrisException {
		Matcher matcher = pattern.matcher(input);
		if(matcher.find())
		{
			queryList1 = matcher.group(1).trim();
			queryList2 = matcher.group(2).trim();
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
		Table a = tc.getTable(queryList1);
		Table b = tc.getTable(queryList2);
		if (a.compareTo(b) != -1){
			name = "intersect_"+a.getName()+"_"+b.getName();
			Table temp = new Table(name);
			temp.addFields(a.getFieldNames());
			temp.addDataset(a.intersectTable(b));
			tc.addTable(name, temp);
		}
		else{
			throw new ChrisException("Tables do not match");
		}
	}
	
	public String getName(){
		return name;
	}
	

}
