/*
 * Restore Command
 */
package one.commands;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import one.main.TableCollection;

// TODO: Auto-generated Javadoc
/**
 * Restores a file.
 */
public class RestoreCommand implements ICommand {

	private Pattern pattern = Pattern.compile("restore(?:.*?)from(?:.*?)\'(.*)\';", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private String fileName;

	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input);
		if(matcher.find())
		{
			fileName = matcher.group(1).trim();
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() throws ChrisException{
		TableCollection tc = null;
		 try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			tc = (TableCollection) in.readObject();
			in.close();
			fileIn.close();
		}catch (IOException e) {
			throw new ChrisException("Error restoring Table Collection");
		} catch (ClassNotFoundException e) {
			throw new ChrisException("Table Collection class not found");
		}
		tc.restoreTables();
	}

}
