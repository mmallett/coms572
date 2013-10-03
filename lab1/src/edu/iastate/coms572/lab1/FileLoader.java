package edu.iastate.coms572.lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Load files in a given directory and return the contents
 * as a string
 * @author matt
 *
 */
public class FileLoader {

	/**
	 * Directory to load files from
	 */
	private String directory;
	
	/**
	 * Create a new FileLoader
	 * @param directory the directory that files should be loaded from. File paths 
	 * are constructed as directory + file name, so the directory should end in / (or \)
	 */
	public FileLoader(String directory){
		this.directory = directory;
	}
	
	/**
	 * Load a file from the FileLoader's directory
	 * @param fileName loads the file with absolute path directory + file name
	 * @return string contents of the file
	 * @throws FileNotFoundException if the file doesn't exist
	 */
	public String load(String fileName) throws FileNotFoundException{
		File file = new File(directory + fileName);
		Scanner s = new Scanner(file);
		StringBuilder sb = new StringBuilder();
		while(s.hasNextLine()){
			sb.append(s.nextLine());
		}
		return sb.toString();
	}
	
}
