package one.commands;

import org.junit.Before;


// TODO: Auto-generated Javadoc
/**
 * JUNIT Test for Define Table Command
 */
public class DefineTableCommandTest extends AbstractCommandTest{
	@Override
	@Before
	public void setUp() throws Exception{
		command = new DefineTableCommand();
		good = new String[]{"define table x having fields (fields);"};
		bad = new String[]{
				"define table x having fields (fields)",
				"define table x having fields fields;",
				"define table x having fields fields",
				"define table x;"
		};
	}

}
