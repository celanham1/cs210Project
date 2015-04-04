package one.main;

import one.datatypes.*;

/**
 * Creates a field based on inputs
 */
public class FieldFactory {
	
	/**
	 * Creates a single field. Sets field to null if 
	 * input does not match a correct type.
	 * @param name the fields name
	 * @param type the fields data type
	 */
	public AbstractType createField(String name, String type) {
		AbstractType field = null;
		if (type.toLowerCase().equals("boolean")){
			field = new BooleanType(name, type);
		}
		else if (type.toLowerCase().matches("char\\((\\d+)\\)")) {
			field = new CharType(name, type);
		}
		else if (type.toLowerCase().matches("integer")) {
			field = new IntegerType(name, type);
		}
		else if (type.toLowerCase().matches("date")) {
			field = new DateType(name, type);
		}
		else if (type.toLowerCase().matches("real")) {
			field = new RealType(name, type);
		}
		else if (type.toLowerCase().matches("varchar")) {
			field = new VarcharType(name, type);
		}
		return field;
	}
	
}
