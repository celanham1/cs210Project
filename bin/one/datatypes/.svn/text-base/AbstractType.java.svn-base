package one.datatypes;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

import one.commands.ChrisException;
import one.main.XMLEncoder;
import one.values.BooleanValue;
import one.values.CharValue;
import one.values.DateValue;
import one.values.IValue;
import one.values.IntegerValue;
import one.values.RealValue;
import one.values.VarcharValue;

/**
 * The Abstract Data Type.
 */
public abstract class AbstractType implements Serializable{

	private static final long serialVersionUID = -5895048748352727337L;
	private String name;
    private String type;
    protected int byteSize;
  
	/**
	 * Instantiates a new abstract type.
	 *
	 * @param name the name
	 * @param type the type
	 */
	public AbstractType(String name, String type){
		this.name = name;
		this.type = type;
	}
	
	/**
	 * Gets the name.
	 * @return the name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Gets the type.
	 * @return the type
	 */
	public String getType(){
		return type;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return name + " " + type;
	}
	
	/**
	 * Gets the xml string.
	 *
	 * @return the xmlString
	 */
	public String getXMLString(){
		String xmlString="";
		xmlString+="\t\t<FIELD>\n";
		xmlString+="\t\t\t<FNAME>"+XMLEncoder.encode(name)+"</FNAME>\n";
		xmlString+="\t\t\t<FTYPE>"+XMLEncoder.encode(type)+"</FTYPE>\n";
		xmlString+="\t\t</FIELD>\n";
		return xmlString;
	}
	
	/**
	 * Gets the byte size
	 *
	 * @return the byte size
	 */
	public int getByteSize(){
		return byteSize;
	}
	
	/**
	 * Writes a value to a binary file
	 * @param raf the RandomAccessFile
	 * @param v the value
	 */
	public void writeVal(long pos,RandomAccessFile raf, IValue v) throws ChrisException{
		try {
			raf.seek(pos);
			v.writeBinary(raf);
		} catch (IOException e) {
			throw new ChrisException("Error writing to binary file",e);
		}
	}
	
	public IValue readVal(RandomAccessFile raf, long ptr) throws ChrisException{
		IValue v = null;
		try {
			raf.seek(ptr);
			if (type.matches("boolean")){
				v = new BooleanValue(raf);
			}
			else if (type.matches("char\\((\\d+)\\)")) {
				v = new CharValue(raf);
			}
			else if (type.matches("integer")) {
				v = new IntegerValue(raf);
			}
			else if (type.matches("date")) {
				v = new DateValue(raf);
			}
			else if (type.matches("real")) {
				v = new RealValue(raf);
			}
			else if (type.matches("varchar")) {
				v = new VarcharValue(raf);
			}
		} catch (IOException e) {
			throw new ChrisException("Error reading from binary file",e);
		}
		return v;
	}
	
	/**
	 * Creates a new IValue.
	 *
	 * @param in the value
	 * @return the IValue
	 * @throws ChrisException the chris exception
	 */
	public IValue makeVal(String in) throws ChrisException{
		IValue v = null;
		if (type.matches("boolean")){
			v = new BooleanValue(in);
		}
		else if (type.matches("integer")) {
			v = new IntegerValue(in);
		}
		else if (type.matches("date")) {
			v = new DateValue(in);
		}
		else if (type.matches("real")) {
			v = new RealValue(in);
		}
		else if (type.matches("varchar")) {
			v = new VarcharValue(in);
		}
		return v;
	}

	
	
	
}
