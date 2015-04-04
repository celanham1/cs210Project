package one.commands;

import org.junit.Before;


// TODO: Auto-generated Javadoc
/**
 * JUNIT Test for Insert Command
 */
public class InsertCommandTest extends AbstractCommandTest{
	@Override
	@Before
	public void setUp() throws Exception{
		command = new InsertCommand();
		good = new String[]{"insert (list) into table;"};
		bad = new String[]{
				"insert (list) into table",
				"insert list into table;",
				"insert list into table"
		};
	}


}
