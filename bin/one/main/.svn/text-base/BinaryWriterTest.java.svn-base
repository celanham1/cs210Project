package one.main;

import static org.junit.Assert.*;

import one.commands.*;
import org.junit.Before;
import org.junit.Test;

/**
 * JUNIT Test for BinaryWriter
 */
public class BinaryWriterTest {

	private ICommand command;
	/**
	 * Sets the up BinaryWriter
	 * @throws ChrisException 
	 */
	@Before
	public void setUp() throws Exception {
		command = new DefineTableCommand();
		command.matches("define table test having fields (a boolean, b char(5), c date, d integer, e real, f varchar);");
		command.execute();
		command = new InsertCommand();
		command.matches("insert (true,'ccccc','03/27/2012',5,5.5,'aaaa') into test;");
		command.execute();
		
	}
	
	/**
	 * Tests BinaryWriter
	 * @throws ChrisException 
	 */
	@Test
	public void test() throws ChrisException {
		Table table = TableCollection.getTC().getTable("test");
		assertTrue(table.getRowBytes()==38);
	}
}
