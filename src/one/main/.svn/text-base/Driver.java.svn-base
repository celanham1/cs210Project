/*
 * The Driver
 */
package one.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import org.xml.sax.SAXException;
import one.commands.*;

/**
 * Drives the program.
 */
public class Driver {
	
	private ICommand[] commands = new ICommand[] {
			new UpdateCommand(),
			new RestoreCommand(),
			new RenameCommand(),
			new ReadCommand(),
			new PrintCommand(),
			new InsertCommand(),
			new ExitCommand(),
			new DropCommand(),
			new DeleteCommand(),
			new DefineTableCommand(),
			new DefineIndexCommand(),
			new BackupCommand()
	};
	
	/**
	 * The main method.
	 *
	 * @param args the arguments of the program
	 * @throws ChrisException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static void main(String[] args) throws ChrisException{
		try{
			File dir = new File("data");
			if(!dir.exists()){
				dir.mkdir();
			}
			File dtd = new File("database.dtd");
			if(!dtd.exists()){
				dtd.createNewFile();
				writeDTD();
			}
			else if (new File("database.xml").exists()){
				new XMLFileReader().saxReader("database.xml");
			}
			new Driver().run();
		} catch (SAXException e) {
			throw new ChrisException("Error reading XML file", e);
		} catch (IOException e) {
			throw new ChrisException("I/O Error", e);
		}
		
	}
	
	private static void writeDTD() throws ChrisException{
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream("database.dtd"));
			out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			out.println("<!ELEMENT DATABASE (TABLE*)>");
			out.println("<!ELEMENT TABLE (Field*)>");
			out.println("<!ATTLIST TABLE name CDATA #IMPLIED>");
			out.println("<!ELEMENT FIELD (FNAME,FTYPE)>");
			out.println("<!ELEMENT FNAME (#PCDATA)>");
			out.println("<!ELEMENT FTYPE (#PCDATA)>");
			out.close();
		} catch (FileNotFoundException e) {
			throw new ChrisException("File not found",e);
		}
	}
	
	/**
	 * Runs through the program until user enters the exit command.
	 *
	 * @throws ChrisException 
	 */
	public void run() throws ChrisException{
			Scanner sc = new Scanner(System.in);
			while(true){
				System.out.print(">");
				match(sc);
			}
	}
	
	/**
	 * Reads in and stores keyboard input.
	 * Keeps reading until it finds a ';' 
	 * @param sc the Scanner input
	 * @return Returns a String containing the read statement
	 */
	public String read(Scanner sc) {
		String statement = "";
		while(sc.useDelimiter(";").hasNext())
		{
			String str = sc.useDelimiter(";").nextLine();
			statement = statement+str;
			if(str.contains(";")){
				break;
			}
			System.out.print(">");
		}
		return statement;
	}
	
	/**
	 * Matches a string with a command statement.
	 * if found executes the command, else returns an error message.
	 *
	 * @param sc the Scanner input
	 * @throws ChrisException 
	 */
	public void match(Scanner sc) throws ChrisException {
		String str = read(sc);
		for(ICommand command: commands)
		{
			if (command.matches(str)){
				try {
					command.execute();
				} catch (ChrisException e) {
					System.out.println(e.getMessage());
				}
				return;
			}
		}
		tryRecursiveParser(str);
	}
	
	private void tryRecursiveParser(String str) throws ChrisException{
		RecursiveParser rp = new RecursiveParser(str);
		if(rp.matches()){
			try{
			rp.parse();
			System.out.println(rp.printTable());
			rp.dropAllTables();
			}catch (ChrisException e){
				System.out.println(e.getMessage());
			}
			return;
		}
		System.out.println("This is not a correct statement");
	}
}
