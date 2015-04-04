package one.datatypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import one.commands.ChrisException;
import one.values.CharValue;
import one.values.IValue;

/**
 * The Char Data Type.
 */
public class CharType extends AbstractType{

	int size;
	
	/**
	 * Instantiates a new char type.
	 *
	 * @param name the name
	 * @param type the type
	 */
	public CharType(String name, String type) {
		super(name, type);
		setSize();
		byteSize = 8;
	}
	
	/**
	 * Sets the size.
	 */
	private void setSize(){
		Pattern pattern = Pattern.compile("char\\((\\d+)\\)");
		Matcher matcher = pattern.matcher(getType());
		if (matcher.find()){
			size = Integer.parseInt(matcher.group(1));
		}
	}
	
	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize(){
		return size;
	}
	
	/* (non-Javadoc)
	 * @see one.datatypes.AbstractType#makeVal(java.lang.String)
	 */
	@Override
	public IValue makeVal(String in) throws ChrisException{
		return new CharValue(in,size);
	}
	
}
