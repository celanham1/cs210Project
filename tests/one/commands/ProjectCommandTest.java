package one.commands;

import org.junit.Before;


// TODO: Auto-generated Javadoc
/**
 * JUNIT Test for Project Command
 */
public class ProjectCommandTest extends AbstractCommandTest{
	
	@Override
	@Before
	public void setUp() throws Exception{
		command = new ProjectCommand();
		good = new String[]{"project x over y;"};
		bad = new String[]{
				"project x over y",
				"project x;"
		};
	}
}
