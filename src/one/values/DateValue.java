package one.values;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import one.commands.ChrisException;

/**
 * The Class DateValue.
 */
public class DateValue implements IValue<DateValue>{

	private Date date;
	private SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	
	/**
	 * Instantiates a new date value.
	 *
	 * @param in the date
	 * @throws ChrisException the chris exception
	 */
	public DateValue(String in) throws ChrisException{
		Pattern pattern = Pattern.compile("\'(.*)\'");
		Matcher match = pattern.matcher(in);
		if (match.find()){
			convertToDate(match.group(1).trim());
		}
		else{
			throw new ChrisException(in+" does not match Date");
		}
	}
	
	/**
	 * Instantiates a new date value.
	 *
	 * @param in the in
	 * @throws ChrisException 
	 */
	public DateValue(RandomAccessFile raf) throws ChrisException{
		try {
			date = new Date(raf.readLong());
		} catch (IOException e) {
			throw new ChrisException("Error reading binary file", e);
		}
	}

	/* (non-Javadoc)
	 * @see one.values.IValue#writeBinary(java.io.RandomAccessFile)
	 */
	public void writeBinary(RandomAccessFile raf) throws ChrisException{
		try {
			raf.writeLong(date.getTime());
		} catch (IOException e) {
			throw new ChrisException("Error writing to binary file", e);
		}
	}
	
	/**
	 * Converts a String to a Date.
	 *
	 * @param in the date
	 * @throws ChrisException the chris exception
	 */
	private void convertToDate(String in) throws ChrisException{
		try {
			df.setLenient(false);
			date = df.parse(in);
		} catch (ParseException e) {
			throw new ChrisException(in+" does not match Date");
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return df.format(date);
	}
	
	
	
	/* (non-Javadoc)
	 * @see one.values.IValue#compareTo(one.values.IValue)
	 */
	@Override
	public int compareTo(DateValue o) {
		return this.date.compareTo(((DateValue) o).date);
	}

}
