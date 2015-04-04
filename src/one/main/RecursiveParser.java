package one.main;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import one.commands.ChrisException;
import one.commands.IQueryList;
import one.commands.IntersectCommand;
import one.commands.JoinCommand;
import one.commands.MinusCommand;
import one.commands.ProjectCommand;
import one.commands.SelectCommand;
import one.commands.UnionCommand;

public class RecursiveParser {
	
	private String queryList, input;
	private ArrayList<String> tableNames = new ArrayList<String>();
	
	private IQueryList[] commands = new IQueryList[] {
			new SelectCommand(),
			new ProjectCommand(),
			new MinusCommand(),
			new JoinCommand(),
			new IntersectCommand(),
			new UnionCommand()
	};
	
	public RecursiveParser(String in){
		input = in;
	}
	
	public String printTable() throws ChrisException{
		String str = "";
		if (!tableNames.isEmpty()){
			Table t = TableCollection.getTC().getTable(tableNames.get(tableNames.size()-1));
			str = t.printTable();
			if(str.isEmpty()){
				throw new ChrisException("No data for table");
			}
		}
		return str;
	}
	
	public void dropAllTables(){
		TableCollection tc = TableCollection.getTC();
		for (String name : tableNames){
			tc.dropTable(name);
		}
	}
	
	public boolean matches() throws ChrisException{
		for(IQueryList command: commands)
		{
			if (command.matches(input)){
			return true;
			}
		}
		return false;
	}
	
	public void parse() throws ChrisException {
		Pattern pattern = Pattern.compile("(\\([^)^(]*?\\))");
		Matcher m = pattern.matcher(input);
		while(m.find()) {
			String inside = m.group(1);
			process(inside);
			if(queryList != null){
				input = input.replace(inside, queryList);
				queryList = null;
				m = pattern.matcher(input);
			}
			else{
				throw new ChrisException("Encountered an error. Enter another command");
			}
		}
		find(input);
	}
	
	private void process(String in) throws ChrisException{
		Pattern p = Pattern.compile("\\((.*)\\)");
		Matcher m = p.matcher(in);
		if (m.find()){
			String s = m.group(1).trim();
			find(s+";");
		}
	}
	
	private void find(String in) throws ChrisException{
		for(IQueryList command: commands)
		{
			if (command.matches(in)){
				try {
					command.execute();
					queryList = command.getName();
					tableNames.add(queryList);
				} catch (ChrisException e) {
					System.out.println(e.getMessage());
				}
				return;
			}
		}
	}

}