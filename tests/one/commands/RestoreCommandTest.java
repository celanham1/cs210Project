package one.commands;


import org.junit.Before;


// TODO: Auto-generated Javadoc
/**
 * JUNIT Test for Restore Command
 */
public class RestoreCommandTest extends AbstractCommandTest{
	
	@Override
	@Before
	public void setUp() throws Exception{
		command = new RestoreCommand();
		good = new String[]{"restore from 'file';"};
		bad = new String[]{
				"restore from 'file'",
				"restore from file;"
		};
	}
	
}
