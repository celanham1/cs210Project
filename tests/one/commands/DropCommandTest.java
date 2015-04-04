package one.commands;

import org.junit.Before;


// TODO: Auto-generated Javadoc
/**
 * JUNIT Test for Drop Command
 */
public class DropCommandTest extends AbstractCommandTest{
	@Override
	@Before
	public void setUp() throws Exception{
		command = new DropCommand();
		good = new String[]{"drop table x;"};
		bad = new String[]{
				"drop table x",
				"drop x;"
		};
	}

}
