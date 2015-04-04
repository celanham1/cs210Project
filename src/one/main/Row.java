package one.main;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

import one.commands.ChrisException;
import one.values.IValue;

/**
 * The Class Row.
 */
public class Row implements Comparable<Row>, Serializable{
	
	private static final long serialVersionUID = 2790332834274084967L;
	private IValue[] values;
	
	/**
	 * Instantiates a new Row.
	 *
	 * @param values the values
	 */
	public Row(IValue[] values){
		this.values = values;
	}
	
	/**
	 * Appends a row to this row
	 *
	 * @param inRow the Row
	 * @return the new Row
	 * @throws ChrisException 
	 */
	public Row appendRow(Row inRow) throws ChrisException{
		IValue[] in = inRow.values;
		IValue[] newRow = new IValue[values.length+in.length];
		for(int i = 0; i<this.values.length;i++){
			newRow[i] = this.values[i];
		}
		for (int i = 0; i< in.length; i++){
			newRow[i+this.values.length] = in[i];
		}
		return new Row(newRow);
	}
	
	public IValue getVal(int pos) throws ChrisException{
		return values[pos];
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		String row = "";
		for(IValue v : values){
			row+=v.toString()+"\t";
		}
		return row;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Row in) {
		for (int i = 0; i < this.values.length; i++){
			if (this.values[i].compareTo(in.values[i]) != 0){
				return -1;
			}
		}
		return 0;
	}

	public void writeRow(long pos, RandomAccessFile raf) throws ChrisException{
		try {
			raf.seek(pos);
			for (IValue v : values){
				v.writeBinary(raf);
			}
			raf.writeByte(0);
			//raf.close();
		} catch (IOException e) {
			throw new ChrisException("Error writing to binary file", e);
		}
	}
}
