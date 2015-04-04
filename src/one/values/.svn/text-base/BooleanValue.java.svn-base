package one.values;

import java.io.IOException;
import java.io.RandomAccessFile;
import one.commands.ChrisException;

/**
 * The Class BooleanValue.
 */
public class BooleanValue implements IValue<BooleanValue>{
	
	private boolean value;
	
	/**
	 * Instantiates a new boolean value.
	 *
	 * @param in the value
	 * @throws ChrisException the chris exception
	 */
	public BooleanValue(String in) throws ChrisException{
		if (in.toLowerCase().equals("true")){
			value = true;
		}
		else if (in.toLowerCase().equals("false")){
			value = false;
		}
		else{
			throw new ChrisException(in+" does not match boolean");
		}
	}
	
	/**
	 * Instantiates a new boolean value.
	 *
	 * @param in the value
	 * @throws ChrisException 
	 */
	public BooleanValue(RandomAccessFile raf) throws ChrisException{
		try {
			value = raf.readBoolean();
		} catch (IOException e) {
			throw new ChrisException("Error reading binary file", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see one.values.IValue#writeBinary(java.io.RandomAccessFile)
	 */
	public void writeBinary(RandomAccessFile raf) throws ChrisException{
		try {
			raf.writeBoolean(value);
		} catch (IOException e) {
			throw new ChrisException("Error writing to binary file", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return new Boolean(value).toString();
	}
	
	
	/* (non-Javadoc)
	 * @see one.values.IValue#compareTo(one.values.IValue)
	 */
	@Override
	public int compareTo(BooleanValue o) {
		boolean i = o.value;
		if (this.value == true && i == false) return 1;
		else if (this.value == false && i == true) return -1;
		return 0;
	}

	
}
