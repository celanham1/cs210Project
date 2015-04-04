package one.commands;


import org.junit.Before;


// TODO: Auto-generated Javadoc
/**
 * JUNIT Test for Delete Command
 */
public class DeleteCommandTest extends AbstractCommandTest{
	@Override
	@Before
	public void setUp() throws Exception{
		command = new DeleteCommand();
		good = new String[]{"delete x where y;",
				"delete x;"};
		bad = new String[]{
				"delete x where y",
				"delete x",
		};
	}

}
