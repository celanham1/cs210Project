package one.commands;

import org.junit.Before;


// TODO: Auto-generated Javadoc
/**
 * JUNIT Test for Update Command
 */
public class UpdateCommandTest extends AbstractCommandTest{
	@Override
	@Before
	public void setUp() throws Exception{
		command = new UpdateCommand();
		good = new String[]{"update x set a = 1 where z;",
				"update x set a = 1;"};
		bad = new String[]{
				"update x set a = 1 where z",
				"update x set a = 1",
				"update x set a where z;"
		};
	}
	
}
