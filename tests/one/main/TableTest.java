package one.main;

import static org.junit.Assert.assertTrue;
import one.commands.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUNIT Test for Table
 */
public class TableTest {
	
	/**
	 * Sets the up table
	 * @throws ChrisException 
	 */
	@Before
	public void setUp() throws ChrisException {
		ICommand command = new DefineTableCommand();
		command.matches("define table test having fields (a boolean, b char(5), c date, d integer, e real, f varchar);");
		command.execute();
	}
	
	/**
	 * Tests the Table method printTableDictionary.
	 * @throws ChrisException 
	 */
	@Test
	public void test() throws ChrisException{
		ITable table = TableCollection.getTC().getTable("test");  
		String d = table.toString();
		String s = "test\n------------------\na boolean\nb char Size: 5\nc date\nd integer\ne real\nf varchar";
		assertTrue(d.equals(s));
	}

}
