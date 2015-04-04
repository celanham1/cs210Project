package one.main;

import static org.junit.Assert.*;


import one.commands.ChrisException;
import org.junit.Before;
import org.junit.Test;


/**
 * JUNIT test for XMLFileReader
 */
public class XMLFileReaderTest {

	/**
	 * Sets up XMLFileREader
	 */
	@Before
	public void setUp() throws Exception {
		new XMLFileReader().saxReader("test.xml");
	}

	/**
	 * Test XMLFileReader method saxReader
	 */
	@Test
	public void test() throws ChrisException {
		String d = TableCollection.getTC().toString();
		String s = "test\n------------------\na integer\nb boolean\nc char Size: 2\nd date\ne real\nf varchar\n\n";
		assertTrue(d.equals(s));
	}

}
