package one.main;

import static org.junit.Assert.*;

import one.commands.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUNIT Test for BinaryReader
 */
public class BinaryReaderTest {
	
	/**
	 * Sets the up BinaryReader
	 * @throws ChrisException 
	 */
	@Before
	public void setUp() throws Exception {
		ICommand command = new DefineTableCommand();
		command.matches("define table test having fields (a boolean, b char(5), c date, d integer, e real, f varchar);");
		command.execute();
		command = new InsertCommand();
		command.matches("insert (true,'ccccc','03/27/2012',5,5.5,'aaaa') into test;");
		command.execute();
	}
	
	/**
	 * Tests BinaryReader
	 * @throws ChrisException 
	 */
	@Test
	public void test() throws ChrisException {
		Table table = TableCollection.getTC().getTable("test");
		String a = table.printTable();
		String b = "a\tb\tc\td\te\tf\t\ntrue\tccccc\t03/27/2012\t5\t5.5\taaaa\t\n";
		assertTrue(a.equals(b));
	}

}
