package one.main;

import java.io.IOException;
import one.commands.ChrisException;
import one.datatypes.AbstractType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Reads a XML file and creates the TableCollection from it
 */
public class XMLFileReader {
	
	/**
	 * Reads in an XML file and creates a table collection from it.
	 *
	 * @param filename the filename
	 * @throws SAXException 
	 * @throws IOException 
	 */
	public void saxReader(String filename) throws SAXException, IOException {
		XMLReader reader = XMLReaderFactory.createXMLReader();
		reader.setContentHandler(new ContentHandler());
		reader.parse(filename);
	}
	
	private class ContentHandler extends DefaultHandler {
	
		private boolean database = false;
		private boolean table = false;
		private boolean field = false;
		private boolean fname = false;
		private boolean ftype = false;
		private String tableName, fieldName, fieldType, ptr;
		private AbstractType abstractField;
		private Table tableObject;
		private TableCollection tc = TableCollection.getTC();
		
		/* (non-Javadoc)
		 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
		 */
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			
				if(qName.equalsIgnoreCase("DATABASE")) {
					database = true;			
				}
				else if(qName.equalsIgnoreCase("TABLE")) {
					table = true;			
				}
				else if(qName.equalsIgnoreCase("FIELD")) {
					field = true;			
				}
				else if(qName.equalsIgnoreCase("FNAME")) {
					fname= true;			
				}
				else if(qName.equalsIgnoreCase("FTYPE")) {
					ftype = true;			
				}
				for(int i = 0;i< attributes.getLength();i++) {
					tableName = attributes.getValue(i);
					tableObject = new Table(tableName);
				}
		}

		/* (non-Javadoc)
		 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
		 */
		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			try{
				if(qName.equalsIgnoreCase("DATABASE")) {
					database = false;
				}
				else if(qName.equalsIgnoreCase("TABLE")) {
					tc.addTable(tableName, tableObject);
					table = false;
				}
				else if(qName.equalsIgnoreCase("FIELD")) {
					field = false;
				}
				else if(qName.equalsIgnoreCase("FNAME")) {
					fname = false;
				}
				else if(qName.equalsIgnoreCase("FTYPE")) {
					abstractField = new FieldFactory().createField(fieldName,fieldType);
					tableObject.add(abstractField);
					ftype = false;
				}
			} catch (ChrisException e) {
				e.printStackTrace();
			}
		}

		/* (non-Javadoc)
		 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
		 */
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			String data = new String(ch, start, length); 
			if(fname) {
				fieldName = data;
			}
			else if(ftype) {
				fieldType = data;
			}
		}
		
	}
}

