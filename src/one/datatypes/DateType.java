package one.datatypes;

/**
 * The Date Data Type.
 */
public class DateType extends AbstractType{

	/**
	 * Instantiates a new date type.
	 *
	 * @param name the name
	 * @param type the type
	 */
	public DateType(String name, String type) {
		super(name, type);
		byteSize = 8;
	}
	

}
