package one.commands;


import org.junit.Before;


// TODO: Auto-generated Javadoc
/**
 * JUNIT Test for Rename Command
 */
public class RenameCommandTest extends AbstractCommandTest{
	
	@Override
	@Before
	public void setUp() throws Exception{
		command = new RenameCommand();
		good = new String[]{"rename table a to b;"};
		bad = new String[]{
				"rename table a to b",
				"rename table a;"
		};
	}
	
}
