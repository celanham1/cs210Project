package one.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * The Abstract JUNIT Test for Commands
 */
public abstract class AbstractCommandTest {

	/** The command. */
	protected ICommand command;
	/** The good. */
	protected String[] good;
	/** The bad. */
	protected String[] bad;
	
	/**
	 * Sets up the command and the Strings good and bad.
	 */
	public abstract void setUp() throws Exception;

	
	/**
	 * Tests the command
	 * @throws ChrisException 
	 */
	@Test
	public void test() throws ChrisException {
		
		for(String s: good){
			assertTrue(command.matches(s));
		}
		for(String s: bad){
			assertFalse(command.matches(s));
		}
	}

}