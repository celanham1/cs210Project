package one.main;

import static org.junit.Assert.assertTrue;
import one.commands.DefineTableCommand;
import one.commands.ICommand;
import org.junit.Before;
import org.junit.Test;

/**
 * JUNIT Test for TableCollection
 */
public class TableCollectionTest {
	
	/**
	 * Sets up the TableCollection 
	 */
	@Before
	public void setUp() throws Exception {
		ICommand command = new DefineTableCommand();
		command.matches("define table test having fields (a boolean, b char(5), c date, d integer, e real, f varchar);");
		command.execute();
	}
	
	/**
	 * Tests the TableCollection method toString.
	 */
	@Test
	public void test() {
		String d = TableCollection.getTC().toString();
		String s = "test\n------------------\na boolean\nb char Size: 5\nc date\nd integer\ne real\nf varchar\n\n";
		assertTrue(d.equals(s));
	}

}
