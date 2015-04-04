package one.values;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import one.commands.ChrisException;

/**
 * The Class CharValue.
 */
public class CharValue implements IValue<CharValue>{
	
	private String value;
	private long stringPtr = 0;

	/**
	 * Instantiates a new char value.
	 *
	 * @param in the value
	 * @param size the size
	 * @throws ChrisException the chris exception
	 */
	public CharValue(String in, int size) throws ChrisException{
		if (in.length()-2 == size){
			Pattern pattern = Pattern.compile("\'(.*)\'");
			Matcher match = pattern.matcher(in);
			if(match.find()){
				value = match.group(1).trim();
			}
		}
		else{
			throw new ChrisException(in+" does not match Char");
		}
	}
	
	/**
	 * Instantiates a new char value.
	 *
	 * @param in the value
	 * @throws ChrisException 
	 */
	public CharValue(RandomAccessFile raf) throws ChrisException{
		try {
			stringPtr = raf.readLong();
			readStringBinary();
		} catch (IOException e) {
			throw new ChrisException("Error reading binary file", e);
		}
	}

	/**
	 * Write string binary.
	 *
	 * @throws ChrisException the chris exception
	 */
	private void writeStringBinary() throws ChrisException{
		try {
			RandomAccessFile raf = new RandomAccessFile("data"+File.separator+"strings.dat","rw");
			stringPtr = raf.length();
			raf.seek(stringPtr);
			raf.writeUTF(value);
			raf.close();
		}catch (IOException e) {
			throw new ChrisException("Error writing to binary file", e);
		}
	}
	
	/**
	 * Read string binary.
	 *
	 * @throws ChrisException the chris exception
	 */
	private void readStringBinary() throws ChrisException{
		try {
			RandomAccessFile raf = new RandomAccessFile("data"+File.separator+"strings.dat","rw");
			raf.seek(stringPtr);
			value = raf.readUTF();
			raf.close();
		}catch (IOException e) {
			throw new ChrisException("Error reading binary file", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see one.values.IValue#writeBinary(java.io.RandomAccessFile)
	 */
	public void writeBinary(RandomAccessFile raf) throws ChrisException{
		try {
			writeStringBinary();
			raf.writeLong(stringPtr);
		} catch (IOException e) {
			throw new ChrisException("Error writing to binary file", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return value;
	}
	
	
	/* (non-Javadoc)
	 * @see one.values.IValue#compareTo(one.values.IValue)
	 */
	@Override
	public int compareTo(CharValue o) {
		return value.compareTo(o.toString());
	}

}
