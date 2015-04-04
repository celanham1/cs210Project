package one.main;

import static org.junit.Assert.*;

import java.io.*;
import java.util.Scanner;
import one.commands.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUNIT test for XMLWriter
 */
public class XMLWriterTest {

	/**
	 * Sets up XMLWriter
	 */
	@Before
	public void setUp() throws Exception {
		ICommand command = new DefineTableCommand();
		command.matches("define table test having fields (a integer, b boolean, c char(2), d date, e real, f varchar);");
		command.execute();
		new XMLWriter().write("test.xml");
	}

	/**
	 * Test XMLWriter method write
	 */
	@Test
	public void test() throws ChrisException{
		try {
			StringBuilder xmlFile = new StringBuilder();
		    Scanner sc = new Scanner(new FileInputStream("test.xml"), "UTF-8");
		    while (sc.hasNextLine()){
		        xmlFile.append(sc.nextLine().trim());
		    }
		    sc.close();
			String test = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE DATABASE SYSTEM \"database.dtd\"><DATABASE><TABLE name=\"test\"><FIELD><FNAME>a</FNAME><FTYPE>integer</FTYPE><FNAME>b</FNAME><FTYPE>boolean</FTYPE><FNAME>c</FNAME><FTYPE>char(2)</FTYPE><FNAME>d</FNAME><FTYPE>date</FTYPE><FNAME>e</FNAME><FTYPE>real</FTYPE><FNAME>f</FNAME><FTYPE>varchar</FTYPE></FIELD></TABLE></DATABASE>";
			assertTrue(test.contentEquals(xmlFile));
		} catch (FileNotFoundException e) {
			throw new ChrisException("File not found",e);
		}
	}

}
