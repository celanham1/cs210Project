package one.commands;

import org.junit.Before;


// TODO: Auto-generated Javadoc
/**
 * JUNIT Test for Select Command
 */
public class SelectCommandTest extends AbstractCommandTest{
	
	@Override
	@Before
	public void setUp() throws Exception{
		command = new SelectCommand();
		good = new String[]{"select x where y;",
				"select x;"};
		bad = new String[]{
				"select x where y",
				"select x"
		};
	}

}
