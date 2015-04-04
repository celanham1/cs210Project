package one.main;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import one.commands.ChrisException;
import one.datatypes.*;
import one.main.BinarySearchTree.Node;
import one.values.IValue;

/**
 * Sets up a Table containing an ArrayList of Fields.
 */
public class Table implements Comparable<Table>, ITable, Serializable{

	private static final long serialVersionUID = -3055803328558978470L;
	private ArrayList<AbstractType> fieldList = new ArrayList<AbstractType>();
	private String name;
	private int fieldPos;
	private Dataset ds = null;

	/**
	 * Instantiates a new table.
	 * @param name the table name
	 * @throws ChrisException 
	 */
	public Table(String name){
		this.name = name;
		fieldList = new ArrayList<AbstractType>();
	}
	
	public Dataset getDataset(){
		return ds;
	}
	
	/**
	 * Sets the name of the table.
	 * @param inName the new name 
	 */
	public void setName(String inName){
		name = inName;
	}
	
	/**
	 * Gets the name of the table.
	 * @param inName the new name 
	 */
	public String getName(){
		return name;
	}
	
	private String getBSTFile(){
		return name+"Tree";
	}
	private String getFileName(){
		return "data"+File.separator+name+".dat";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		String line = "------------------";
		String dictionary = name+"\n"+line;
		for(AbstractType field : fieldList){
		     dictionary += "\n"+field.toString();
		} 
		return dictionary;
	}
	
	
	public void addFields(String extendedFieldList) throws ChrisException{
		for(String seq : extendedFieldList.split(",") ) {
			String[] tokens = seq.trim().split(" ");
			AbstractType field = new FieldFactory().createField(tokens[0].trim(),tokens[1].trim());
			if (findErrors(tokens[0],this,field)){
				return;
			}
			add(field); 
		}
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
	
	/**
	 * Adds a Field to the ArrayList.
	 * @param field the Field
	 */
	public void add(AbstractType field) throws ChrisException{
		fieldList.add(field);
	}
	
	/**
	 * Checks for duplicate field names in a table
	 * @return true, if there exists duplicate fields
	 */
	public boolean checkDuplicateField(String name){
		for(AbstractType field : fieldList){
			if(field.getName().equals(name))
				return true;
		}
		return false;
	}
	
	/**
	 * Creates XML string of all the fields in the table
	 * @return the xml String
	 */
	public String fieldsXMLString(){
		String xmlString="";
		for (AbstractType field : fieldList){
			xmlString+=field.getXMLString();
		 }
		return xmlString;
	}
	
	
	/**
	 * Gets the total number of bytes in a row
	 * @return row bytes
	 */
	public long getRowBytes() throws ChrisException{
		int totRowBytes = 0;
		for (AbstractType field : fieldList){
			totRowBytes+=field.getByteSize();
		}
		return totRowBytes+1;
	}
	
	/**
	 * Gets the number of rows in the table
	 * @return number rows
	 */
	public int getNumRows() throws ChrisException{
		int numRows = 0;
		try {
			RandomAccessFile raf = new RandomAccessFile(getFileName(),"rw");
			numRows = (int) (raf.length() / (getRowBytes()));
			raf.close();
		} catch (IOException e) {
			throw new ChrisException("Error reading binary file", e);
		}
		return numRows;
	}
	
	private String printFieldNames(){
		String table = "";
		for(AbstractType field : fieldList){
			table+=field.getName()+"\t";
		}
		table+="\n";
		return table;
	}
	
	
	/**
	 * Prints the table with rows of data
	 * @return table
	 */
	public String printTable() throws ChrisException{
		String table = "";
		table+=printFieldNames();
		if(ds==null) table+=printRows();
		else table+=ds.toString();
		return table;
	}
	
	public String getFieldNames(){
		String fields = "";
		for(AbstractType field : fieldList){
			fields+=field.getName()+" "+field.getType();
			if (field != fieldList.get(fieldList.size()-1)){
				fields+=", ";
			}
		}
		return fields;
	}
	
	private String printRow(IValue[] row){
		String table = "";
		for(IValue v : row){
			table+=v.toString()+"\t";
		}
		table+="\n";
		return table;
	}
	
	private String printRows() throws ChrisException{
		String str = "";
		for (int r = 0; r < getNumRows(); r++){
			if(!isDeleted(r)){
			str+=printRow(readRow(r));
			}
		}
		if(str.isEmpty()){
			throw new ChrisException("No data for table "+name);
		}
		return str;
	}
	
	public int getFieldPosition(String name) throws ChrisException{
		int pos = -1;
		for (int i = 0; i< fieldList.size();i++){
			if(fieldList.get(i).getName().equals(name)){
				pos = i;
			}
		}
		if (pos == -1){
			throw new ChrisException("No fields match "+name);
		}
		return pos;
	}
	/**
	 * Returns a list of rows that match the expression
	 * @param name the Field name
	 * @param operator the operator
	 * @param value the value
	 * @return ArrayList of row numbers
	 */
	public ArrayList<Integer> getSelectRows(String name, String operator, String value) throws ChrisException{
		fieldPos = getFieldPosition(name);
		IValue v = fieldList.get(fieldPos).makeVal(value);
		return findSelectedRows(v,operator);
	}
	
	private ArrayList<Integer> findSelectedRows(IValue val,String operator) throws ChrisException{
		IValue v = null;
		int numRows;
		if(ds!=null) numRows = ds.getNumRows();
		else numRows = getNumRows();
		ArrayList<Integer> selectRowNums = new ArrayList<Integer>();
		for (int r = 0; r < numRows; r++){
			if(ds!=null) v = ds.getRow(r).getVal(fieldPos);
			else{
				if(!isDeleted(r)) v = readSingleVal(r,fieldPos);
			}
			if(performOperation(operator,v.compareTo(val))){
				 selectRowNums.add(r);
			}
		}
		if(selectRowNums.isEmpty()){
			throw new ChrisException("No values match the operation"); 
		}
		return selectRowNums;
	}
	
	private boolean performOperation(String operator, int result){
		if(operator.equals(">") && result == 1){
			return true;
		}
		else if(operator.equals(">=") && (result >= 0)){
			return true;
		}
		else if(operator.equals("<") && result == -1){
			return true;
		}
		else if(operator.equals("<=") && (result <= 0)){
			return true;
		}
		else if(operator.equals("=") && result == 0){
			return true;
		}
		else if(operator.equals("!=") && result != 0){
			return true;
		}
		return false;
	}
	
	public Dataset selectRows(ArrayList<Integer> rowNums) throws ChrisException{
		Dataset ds = new Dataset();
		for (Integer i : rowNums){
			if(this.ds != null){
				ds.addRow(this.ds.getRow(i));
			}
			else{
			ds.addRow(new Row(readRow(i)));
			}
		}
		return ds;
	}
	
	/**
	 * Deletes all rows in a table
	 */
	public void deleteAllRows() throws ChrisException{
		try {
			RandomAccessFile raf = new RandomAccessFile(getFileName(),"rw");
			for (int r=0; r <= getNumRows(); r++){
				deleteRow(r,raf);
			}
			raf.close();
		} catch (IOException e) {
			throw new ChrisException("Error writing to binary file", e);
		}
	}
	
	/**
	 * Deletes selected rows from a table
	 * @param selectedRows
	 */
	public void deleteSelectRows(ArrayList<Integer> selectedRows) throws ChrisException{
		try {
			RandomAccessFile raf = new RandomAccessFile(getFileName(),"rw");
			for (Integer i : selectedRows){
				deleteRow(i,raf);
			}
			raf.close();
		} catch (IOException e) {
			throw new ChrisException("Error writing to binary file", e);
		}
	}
	/**
	 * Deletes a row in the table
	 */
	public void deleteRow(int rowNum,RandomAccessFile raf) throws ChrisException{
		long pos = (getRowBytes()+1 * rowNum)-1;
		try {
			raf.seek(pos);
			raf.writeByte(1);
		} catch (IOException e) {
			throw new ChrisException("Error writing to binary file", e);
		}
	}
	
	
	public boolean isDeleted (int rowNum) throws ChrisException{
		long pos = (getRowBytes()+1 * rowNum)-1;
		try {
			RandomAccessFile raf = new RandomAccessFile(getFileName(),"rw");
			raf.seek(pos);
			if (raf.readByte()==1){
				return true;
			}
			raf.close();
		} catch (IOException e) {
			throw new ChrisException("Error reading from binary file", e);
		}
		return false;
	}
	
	/**
	 * Updates a row with a new value
	 * @param rowNum the row number
	 * @param fieldName the field name
	 * @param inValue the new value
	 */
	public void updateValue(int rowNum,String fieldName,String inValue) throws ChrisException{
		if(!isDeleted(rowNum)){
			fieldPos = getFieldPosition(fieldName);
			IValue v = fieldList.get(fieldPos).makeVal(inValue);
			writeSingleVal(rowNum,v);
		}
	}
	
	private void writeSingleVal(int rowNum, IValue in) throws ChrisException{
		try {
			long ptr = calcValPos(rowNum,fieldPos);
			RandomAccessFile raf = new RandomAccessFile(getFileName(),"rw");
			fieldList.get(fieldPos).writeVal(ptr, raf, in);
			raf.close();
		} catch (IOException e) {
			throw new ChrisException("Error writing to binary file", e);
		}
	}
	
	private IValue readSingleVal(int row, int fieldPos) throws ChrisException{
		IValue v = null;
		try{
			long ptr = calcValPos(row, fieldPos);
			RandomAccessFile raf = new RandomAccessFile(getFileName(),"rw");
			v = fieldList.get(fieldPos).readVal(raf,ptr);
			raf.close();
		}catch (IOException e){
			throw new ChrisException("Error reading from binary file");
		}
		return v;
	}
	
	private long calcValPos(int row, int pos) throws ChrisException{
		long bytes = getRowBytes()*row;
		long offset = 0;
		for (int i=0; i<pos; i++){
			offset+=fieldList.get(i).getByteSize();
		}
		return bytes+offset;
	}
	
	/**
	 * Creates a row of Values
	 * @return the row pointer
	 * @throws ChrisException 
	 */
	public IValue[] createValues(String valueList) throws ChrisException{
		IValue[] row = new IValue[fieldList.size()];
		String[] tokens = valueList.split(",");
		if (fieldList.size() != tokens.length){
			throw new ChrisException("Invalid number of values");
		}
		for(int i = 0; i< tokens.length;i++){
			row[i] = fieldList.get(i).makeVal(tokens[i].trim());
		}
		return row;
	}
	
	/**
	 * Write data to binary file
	 * @param values the values
	 * @return the row pointer
	 * @throws ChrisException 
	 */
	public void writeRow(IValue[] values, int row) throws ChrisException{
		try {
			RandomAccessFile raf = new RandomAccessFile(getFileName(),"rws");
			raf.seek(getRowBytes()*row);
			for (IValue v : values){
				v.writeBinary(raf);
			}
			raf.writeByte(0);
			raf.close();
		} catch (IOException e) {
			throw new ChrisException("Error writing to binary file", e);
		}
	}
	
	/**
	 * Read row from binary file
	 * @param ptr the row pointer
	 * @return array of values
	 * @throws ChrisException 
	 */
	public IValue[] readRow(int row) throws ChrisException{
		IValue[] vals;
		try{
			RandomAccessFile raf = new RandomAccessFile(getFileName(),"rw");
			raf.seek(getRowBytes()*row);
			vals = new IValue[fieldList.size()];
			for(int i=0;i<fieldList.size();i++){
				vals[i] = fieldList.get(i).readVal(raf,raf.getFilePointer());
			}
			raf.readByte();
			raf.close();
		}catch(IOException e){
			throw new ChrisException("Error reading binary file", e);
		}
		return vals;
	}
	
	/**
	 * Gets a table projected over selected fields 
	 * @param inFields the fields
	 * @return the table
	 * @throws ChrisException 
	 */
	public Dataset getProjectedRows(String inFields) throws ChrisException{
		ArrayList<Integer> colNums = selectColFields(inFields);
		Dataset data;
		if(this.ds != null){
			data = ds.projectRows(colNums,this);
		}
		else{
		 data = projectRows(colNums,this);
		 if(data.toString().isEmpty()){
			throw new ChrisException("No data for table "+name);
		 }
		}
		return data;
	}
	
	public ArrayList<Integer> selectColFields(String inFields) throws ChrisException{
		String[] tokens = inFields.split(",");
		ArrayList<Integer> fieldCols = new ArrayList<Integer>();
		for (String seq : tokens){
			int i = getFieldPosition(seq.trim());
			fieldCols.add(i);
		}
		Collections.sort(fieldCols);
		return fieldCols;
	}
	
	public String printColNames(ArrayList<Integer> colNums){
		String str = "";
		for (int c : colNums){
			str+=fieldList.get(c).getName()+" "+fieldList.get(c).getType();
			if (c != colNums.get(colNums.size()-1)){
				str+=", ";
			}
		}
		return str;
	}
	
	/* (non-Javadoc)
	 * @see one.main.ITable#projectRows(java.util.ArrayList)
	 */
	@Override
	public Dataset projectRows(ArrayList<Integer> colNums, Table a) throws ChrisException{
		Dataset ds = new Dataset();
		Row row;
		for(int r = 0; r < getNumRows(); r++){
			if(!isDeleted(r)){
				row = new Row(readColValues(colNums,r));
				ds.addRow(row);
			}
		}
		return ds;
	}
	
	private IValue[] readColValues(ArrayList<Integer> colNums, int rowNum) throws ChrisException{
		IValue[] vals = new IValue[colNums.size()];
		try{
			RandomAccessFile raf = new RandomAccessFile(getFileName(),"rw");
			for(int i=0;i<colNums.size();i++){
				vals[i]=readSingleVal(rowNum,colNums.get(i));
			}
			raf.close();
		}catch(IOException e){
			throw new ChrisException("Error reading binary file", e);
		}
		return vals;
	}
	
	/**
	 * Gets a String of fields in the formant name.field
	 * @return the table
	 */
	public String getJoinedFields(){
		String str = "";
		for (AbstractType f : this.fieldList){
			str+=name+"."+f.getName()+" "+f.getType();
			if (f != fieldList.get(fieldList.size()-1)){
				str+=", ";
			}
		}
		return str;
	}
	
	public Row getRow(int rowNum) throws ChrisException{
		return new Row(readRow(rowNum));
	}
	
	
	public boolean inTable(Row a, Table b) throws ChrisException{
		for (int j = 0; j < b.getNumRows(); j++){
			if (a.compareTo(b.getRow(j)) == 0){
				return true;
			}
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see one.main.ITable#joinTable(one.main.Table)
	 */
	@Override
	public Dataset joinTable(Table b) throws ChrisException{
		Dataset ds = new Dataset();
		if(this.ds != null){
			ds = this.ds.joinTable(b);
		}
		else if(b.ds!=null){
			ds = b.ds.joinTable(this);
		}
		Row row;
		for(int r = 0; r<getNumRows(); r++){
			if(!this.isDeleted(r)){
				row = getRow(r);
				for (int j = 0; j<b.getNumRows(); j++){
					ds.addRow(row.appendRow(b.getRow(j)));
				}
			}
		}
		return ds;
	}
	
	/* (non-Javadoc)
	 * @see one.main.ITable#minusTable(one.main.Table)
	 */
	@Override
	public Dataset minusTable(Table b) throws ChrisException{
		Dataset ds = new Dataset();
		if(this.ds != null){
			ds = this.ds.minusTable(b);
		}
		else if(b.ds!=null){
			ds = b.ds.minusTable(this);
		}
		Row rowA;
		for (int r = 0; r < getNumRows(); r++){
			if(!this.isDeleted(r)) {
				rowA = getRow(r);
				if (!inTable(rowA,b)) ds.addRow(rowA);
			}
		}
		return ds;
	}
	
	/* (non-Javadoc)
	 * @see one.main.ITable#unionTable(one.main.Table)
	 */
	@Override
	public Dataset unionTable(Table b) throws ChrisException{
		Dataset ds = new Dataset();
		if(this.ds != null){
			ds = this.ds.unionTable(b);
		}
		else if(b.ds!=null){
			ds = b.ds.unionTable(this);
		}
		else{
			addRowsA(ds);
			Row rowB;
			for (int r = 0; r < b.getNumRows(); r++){
				if(!b.isDeleted(r)) {
					rowB = b.getRow(r);
					if (!inTable(rowB,this)) ds.addRow(rowB);
				}
			}
		}
		return ds;
	}
	
	private void addRowsA(Dataset ds) throws ChrisException{
		for (int r = 0; r < getNumRows(); r++){
			if(!isDeleted(r)) ds.addRow(getRow(r));
		}
	}
	
	/* (non-Javadoc)
	 * @see one.main.ITable#intersectTable(one.main.Table)
	 */
	@Override
	public Dataset intersectTable(Table b) throws ChrisException{
		Dataset ds = new Dataset();
		if(this.ds != null){
			ds = this.ds.intersectTable(b);
		}
		else if(b.ds!=null){
			ds = b.ds.intersectTable(this);
		}
		Row rowA;
		for (int r = 0; r < getNumRows(); r++){
			if(!isDeleted(r)) {
				rowA = getRow(r);
				if (inTable(rowA,b)) ds.addRow(rowA);
			}
		}
		return ds;
	}
	
	public void addDataset(Dataset ds){
		this.ds = ds;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Table in) {
		if (this.fieldList.size() != in.fieldList.size()) return -1;
		else{
			for (int i = 0; i < this.fieldList.size(); i++){
				if (!this.fieldList.get(i).getType().equals(in.fieldList.get(i).getType())){
					return -1;
				}
			}
		}
		return 0;
	}
	
	public void saveToDataset() throws ChrisException{
		Dataset d = new Dataset();
		for (int r = 0; r < this.getNumRows(); r++){
			if(!this.isDeleted(r)) d.addRow(this.getRow(r));
		}
		ds = d;
	}
	
	public void writeDataset() throws ChrisException{
		if(ds!=null){
			try{
				long pos;
				RandomAccessFile raf = new RandomAccessFile(getFileName(),"rw");
				for (int r = 0; r < ds.getNumRows(); r++){
					pos = getRowBytes()*r;
					ds.getRow(r).writeRow(pos, raf);
				}
				raf.close();
			}catch(IOException e){
				throw new ChrisException("Error writing to binary file", e);
			}
		}
	}
	
	private long writeNodePtr(long val) throws ChrisException{
		long ptr;
		try{
			RandomAccessFile raf = new RandomAccessFile(getBSTFile(),"rw");
			ptr = raf.getFilePointer();
			raf.writeLong(val);
			raf.close();
		}catch(IOException e){
			throw new ChrisException("Error reading binary file", e);
		}
		return ptr;
	}
	
	public void writeParentNode(long index) throws ChrisException{
		Node n = null;
		long ptr = writeNodePtr(index);
		n.setValue(ptr);
		n.setParent(ptr);
	}
	
	public void traverseTable(int index){
		
	}

}
