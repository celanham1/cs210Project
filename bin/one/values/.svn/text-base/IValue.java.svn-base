package one.values;

import java.io.RandomAccessFile;
import java.io.Serializable;

import one.commands.ChrisException;

/**
 * The Interface IValue.
 */
public interface IValue<T extends IValue<T>> extends Comparable<T>, Serializable{
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString();
	
	/**
	 * Writes value a binary file.
	 *
	 * @param raf the RandomAccessFile
	 * @throws ChrisException the chris exception
	 */
	public void writeBinary(RandomAccessFile raf) throws ChrisException;
	
	/**
	 * Compares this IValue with another IValue.
	 *
	 * @param o the IValue to be compared to
	 * @return the int
	 */
	public int compareTo(T o);
	
	
}
