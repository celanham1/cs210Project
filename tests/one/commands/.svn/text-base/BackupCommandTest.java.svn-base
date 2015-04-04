package one.commands;


import org.junit.Before;



// TODO: Auto-generated Javadoc
/**
 * JUNIT Test for Backup Command
 */
public class BackupCommandTest extends AbstractCommandTest{
	
	@Override
	@Before
	public void setUp() throws Exception{
		command = new BackupCommand();
		good = new String[]{"backup to 'file';",};
		bad = new String[]{
				"backup to 'file'",
				"backup to file;",
				"backup to file"	
		};
	}

}
