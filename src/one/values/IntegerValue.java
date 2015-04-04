package one.values;

import java.io.IOException;
import java.io.RandomAccessFile;
import one.commands.ChrisException;

/**
 * The Class IntegerValue.
 */
public class IntegerValue implements IValue<IntegerValue> {

	private int value;

	/**
	 * Instantiates a new integer value.
	 *
	 * @param in the value
	 * @throws ChrisException the chris exception
	 */
	public IntegerValue(String in) throws ChrisException{
		try{
			value = Integer.parseInt(in);
		}catch(NumberFormatException e) {
			throw new ChrisException(in+" does not match Integer");
		}
	}
	
	/**
	 * Instantiates a new integer value.
	 *
	 * @param val the val
	 * @throws ChrisException 
	 */
	public IntegerValue(RandomAccessFile raf) throws ChrisException{
		try {
			value = raf.readInt();
		} catch (IOException e) {
			throw new ChrisException("Error reading binary file", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see one.values.IValue#writeBinary(java.io.RandomAccessFile)
	 */
	public void writeBinary(RandomAccessFile raf) throws ChrisException{
		try {
			raf.writeInt(value);
		} catch (IOException e) {
			throw new ChrisException("Error writing to binary file", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return Integer.toString(value);
	}
	
	
	
	/* (non-Javadoc)
	 * @see one.values.IValue#compareTo(one.values.IValue)
	 */
	@Override
	public int compareTo(IntegerValue o) {
		int i = o.value;
		if (this.value < i) return -1;
		else if (this.value > i) return 1;
		return 0;
	}




	
}
