package one.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Writes the TableCollection into an XML file
 */
public class XMLWriter {

	/**
	 * Writes all the tables from the Table Collection into an XML file
	 *
	 * @param fileName the fileName
	 * @throws FileNotFoundException 
	 */
	public void write(String fileName) throws FileNotFoundException {
		String ENCODING = "UTF-8";
		PrintWriter out = new PrintWriter(new FileOutputStream(fileName));
		out.println("<?xml version=\"1.0\" encoding=\""+ENCODING+"\"?>");
		out.println("<!DOCTYPE DATABASE SYSTEM \"database.dtd\">");
		out.println("<DATABASE>");
		out.print(TableCollection.getTC().tablesXMLString());
		out.println("</DATABASE>");
		out.close();
	}
}
