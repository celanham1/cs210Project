/*
 * Define Table Command
 */
package one.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import one.datatypes.AbstractType;
import one.main.*;

/**
 * Defines a Table with multiple Fields 
 */
public class DefineTableCommand implements ICommand {
	
	private Pattern pattern = Pattern.compile("define(?:.*?)table(.*)having(?:.*?)fields(?:.*?)\\((.*)\\);", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private String tableName, extendedFieldList;
	
	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		
		Matcher matcher = pattern.matcher(input);
		if(matcher.find())
		{
			tableName = matcher.group(1).trim();
			extendedFieldList = matcher.group(2).trim();
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() throws ChrisException {
		if (extendedFieldList.isEmpty() || tableName.isEmpty()){
			throw new ChrisException("Error in number of arguments");
		}
		Table table = new Table(tableName);
		table.addFields(extendedFieldList);
		TableCollection.getTC().addTable(tableName, table);	
		
	}
	
	private boolean findErrors(String name, Table table, AbstractType field) throws ChrisException {
		if(field == null){
			throw new ChrisException("Incorrect Type.");
		}
		if (table.checkDuplicateField(name)){
			throw new ChrisException("Duplicate Fields");
	 	}
		return false;
	}
	
}
