package one.main;

import java.io.Serializable;
import java.util.ArrayList;

import one.commands.ChrisException;
import one.values.IValue;

/**
 * The Class Dataset.
 */
public class Dataset implements ITable, Serializable {
	
	private static final long serialVersionUID = -7287124234508383749L;
	private ArrayList<Row> rowList = new ArrayList<Row>();
	
	/**
	 * Instantiates a new Dataset.
	 */
	public Dataset(){
	}
	
	/**
	 * Adds a row into the Dataset
	 *
	 * @param in the Row
	 */
	public void addRow(Row in){
		rowList.add(in);
	}
	
	public Row getRow(int rowNum){
		return rowList.get(rowNum);
	}
	
	public int getNumRows(){
		return rowList.size();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		String dataSet = "";
		for (Row r : rowList){
			dataSet+=r.toString()+"\n";
		}
		return dataSet;	
	}
	
	@Override
	public Dataset projectRows(ArrayList<Integer> colNums, Table a)
			throws ChrisException {
		Dataset ds = new Dataset();
		Row row;
		for(int r = 0; r < getNumRows(); r++){
			row = new Row(readColValues(colNums,r));
			ds.addRow(row);
		}
		return ds;
	}
	
	private IValue[] readColValues(ArrayList<Integer> colNums, int r) throws ChrisException{
		IValue[] vals = new IValue[colNums.size()];
		Row row = this.rowList.get(r);
		for(int i=0;i<colNums.size();i++){
			vals[i]= row.getVal(colNums.get(i));
		}
		return vals;
	}

	@Override
	public Dataset joinTable(Table b) throws ChrisException {
		Dataset ds = new Dataset();
		Row row;
		if (b.getDataset()!=null){
			Dataset db = b.getDataset();
			for (int r = 0; r < getNumRows() ; r++){
				row = this.getRow(r);
				for (int j = 0; j<db.getNumRows(); j++){
					ds.addRow(row.appendRow(db.getRow(j)));
				}
			}
		}
		for(int r = 0; r<getNumRows(); r++){
			row = getRow(r);
			for (int j = 0; j<b.getNumRows(); j++){
				ds.addRow(row.appendRow(b.getRow(j)));
			}
		}
		return ds;
	}

	@Override
	public Dataset minusTable(Table b) throws ChrisException {
		Dataset ds = new Dataset();
		Row rowA;
		if (b.getDataset()!=null){
			Dataset db = b.getDataset();
			for (int r = 0; r < db.getNumRows() ; r++){
				rowA = this.getRow(r);
				if (!inTable(rowA,db)) ds.addRow(rowA);
			}
		}
		else{
			for (int r = 0; r < this.getNumRows(); r++){
				rowA = this.getRow(r);
				if (!b.inTable(rowA,b)) ds.addRow(rowA);
			}
		}
		return ds;
	}

	@Override
	public Dataset unionTable(Table b) throws ChrisException {
		Dataset ds = this;
		Row rowB;
		if (b.getDataset()!=null){
			Dataset db = b.getDataset();
			for (int r = 0; r < db.getNumRows() ; r++){
				rowB = db.getRow(r);
				if (!inTable(rowB,this)) ds.addRow(rowB);
			}
		}
		else{
			for (int r = 0; r < b.getNumRows(); r++){
				rowB = b.getRow(r);
				if (!inTable(rowB,this)) ds.addRow(rowB);
			}
		}
		return ds;
	}

	@Override
	public Dataset intersectTable(Table b) throws ChrisException {
		Dataset ds = new Dataset();
		Row rowA;
		if (b.getDataset()!=null){
			Dataset db = b.getDataset();
			for (int r = 0; r < db.getNumRows() ; r++){
				rowA = db.getRow(r);
				if (inTable(rowA,this)) ds.addRow(rowA);
			}
		}
		else{
			for (int r = 0; r < getNumRows(); r++){
				if(!b.isDeleted(r)) {
					rowA = getRow(r);
					if (b.inTable(rowA,b)) ds.addRow(rowA);
				}
			}
		}
		return ds;
	}
	
	private boolean inTable(Row a, Dataset b) throws ChrisException{
		for (int j = 0; j < b.getNumRows(); j++){
			if (a.compareTo(b.getRow(j)) == 0){
				return true;
			}
		}
		return false;
	}
}
