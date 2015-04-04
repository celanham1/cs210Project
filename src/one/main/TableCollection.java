package one.main;

import java.util.HashMap;
import one.commands.ChrisException;
import java.io.Serializable;

/**
 * Sets up a TableCollection containing a HashMap of all Tables.
 */
public class TableCollection implements Serializable{
	
	private static final long serialVersionUID = 2920355344113411109L;
	private HashMap<String, Table> hm = new HashMap<String, Table>();
	private static TableCollection tc  = null;
	
	/**
	 * Instantiates a new table collection.
	 */
	private TableCollection(){}
	
	/**
	 * Gets the Table Collection.
	 * @return tC the Table Collection
	 */
	public static TableCollection getTC(){
		if (tc == null){
			tc = new TableCollection();
		}
		return tc;
	}
	
	/**
	 * Adds a table to hashmap.
	 * @param name the table name
	 * @param table the table object
	 * @throws ChrisException 
	 */
	public void addTable(String name, Table table) throws ChrisException {
		if(hm.get(name)==null){
			hm.put(name, table);
		}
		else{
			throw new ChrisException("Table " + name + " already exists");
		}
	}
	
	/**
	 * Drops a table from the hashmap.
	 * @param name the table name
	 */
	public void dropTable(String name){
		hm.remove(name);
	}
	
	
	/**
	 * Renames a table inside the hashmap.
	 * @param oldName the original table name
	 * @param newName the new table name 
	 * @throws ChrisException 
	 */
	public void renameTable(String oldName, String newName) throws ChrisException {
		Table table = hm.get(oldName);
		if (table!=null){
			table.setName(newName);
			addTable(newName,table);
			dropTable(oldName);
		}
	}
	
	/**
	 * Gets the table.
	 * @param name the table name
	 * @return the table
	 * @throws ChrisException 
	 */
	public Table getTable(String name) throws ChrisException{
		Table table = (Table) hm.get(name);
		if(table == null){
			throw new ChrisException("Table does not exist");
		}
		return table;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		String dictionary = "";
		 for( ITable table: hm.values()) {
		      dictionary += table.toString()+"\n\n";        
		  }
		 return dictionary;
	}
	
	public void backupTables() throws ChrisException{
		for (Table table: hm.values())
		{
			table.saveToDataset();
		}
	}
	
	public void restoreTables() throws ChrisException{
		for (Table table: hm.values())
		{
			table.writeDataset();
		}
	}
	
	/**
	 * Creates XML string of all the tables in the table collection
	 * @return the xml String
	 */
	public String tablesXMLString(){
		String xmlString = "";
		for (Table table: hm.values())
		{
		  xmlString+="\t<TABLE name=\""+table.getName()+"\">\n";
		  xmlString+=table.fieldsXMLString();
		  xmlString+="\t</TABLE>";
		}
		return xmlString;
				
	}
}
