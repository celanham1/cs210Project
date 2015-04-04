/*
 * Backup Command
 */
package one.commands;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import one.main.TableCollection;

// TODO: Auto-generated Javadoc

/**
 * Allows user to backup to a file.
 */
public class BackupCommand implements ICommand {

	private Pattern pattern = Pattern.compile("backup to(?:.*?)\'(.*)\';", Pattern.CASE_INSENSITIVE |Pattern.DOTALL);
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
	public void execute() throws ChrisException {
		TableCollection tc = TableCollection.getTC();
		tc.backupTables();
		 try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(tc);
			out.close();
			fileOut.close();
		}catch (IOException e) {
			throw new ChrisException("Error backing up Table Collection");
		}
	}

}
