package one.values;

import java.io.IOException;
import java.io.RandomAccessFile;
import one.commands.ChrisException;

/**
 * The Class RealValue.
 */
public class RealValue implements IValue<RealValue>{

	private double value;

	/**
	 * Instantiates a new real value.
	 *
	 * @param in the value
	 * @throws ChrisException the chris exception
	 */
	public RealValue(String in) throws ChrisException{
		try{
			value = Double.parseDouble(in);
		}catch(NumberFormatException e) {
			throw new ChrisException(in+" does not match Real");
		}
	}
	
	/**
	 * Instantiates a new real value.
	 * @param in the value
	 * @throws ChrisException 
	 */
	public RealValue(RandomAccessFile raf) throws ChrisException{
		try {
			value = raf.readDouble();
		} catch (IOException e) {
			throw new ChrisException("Error reading binary file", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see one.values.IValue#writeBinary(java.io.RandomAccessFile)
	 */
	public void writeBinary(RandomAccessFile raf) throws ChrisException{
		try {
			raf.writeDouble(value);
		} catch (IOException e) {
			throw new ChrisException("Error writing to binary file", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return Double.toString(value);
	}
	
	
	/* (non-Javadoc)
	 * @see one.values.IValue#compareTo(one.values.IValue)
	 */
	@Override
	public int compareTo(RealValue o) {
		double i = o.value;
		if (this.value < i) return -1;
		else if (this.value > i) return 1;
		return 0;
	}
}
