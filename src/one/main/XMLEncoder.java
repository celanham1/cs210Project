package one.main;

import java.io.FileNotFoundException;
import java.util.HashMap;


/**
 * The Class XMLEncoder.
 */
public class XMLEncoder {
	
	private static HashMap<String, String> codes;
	
	static {
		codes = new HashMap<String, String>();
		codes.put("\"", "&quot;");
		codes.put("&", "&amp;");
		codes.put("'", "&apos;");
		codes.put("<", "&lt;");
		codes.put(">", "&gt;");
	}
	
	private XMLEncoder(){}
	
	/**
	 * Encodes an XML file
	 */
	public static String encode(String input) {
		for(String x : codes.keySet()) {
			input = input.replace(x, codes.get(x));
		}
		return input;
	}
}
