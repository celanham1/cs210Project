package one.commands;


import org.junit.Before;


// TODO: Auto-generated Javadoc
/**
 * JUNIT Test for Define Index Command
 */
public class DefineIndexCommandTest extends AbstractCommandTest {
	@Override
	@Before
	public void setUp() throws Exception{
		command = new DefineIndexCommand();
		good = new String[]{"define index on table (field);"};
		bad = new String[]{
				"define index on table (field)",
				"define index on table field;",
				"define index on table;",
				"define index on table;"
		};
	}

}
