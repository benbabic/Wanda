package fr.irit.wanda.dao;

import java.io.File;


import org.apache.commons.fileupload.FileItem;

import fr.irit.wanda.exception.FileSavingException;

public class FileAO extends DAO {
	
	private static String DESTINATION_DIR_PATH = "/usr/local/wanda/videos";

	
	public static String save(FileItem fileToSave, String id, String format) throws FileSavingException{
		String finalPath = DESTINATION_DIR_PATH+"/"+id+"."+format;
		File destinationFile = new File(finalPath);
		try {
			if (destinationFile.createNewFile()){
				fileToSave.write(destinationFile);
			}
		} catch (Exception e) {
			// why? oh my god why would that happen?
			e.printStackTrace();
			throw new FileSavingException();
		}
		
		return finalPath;
	}
}
