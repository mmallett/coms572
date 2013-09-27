package edu.iastate.coms572.lab1.util;

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

	private String directory;
	
	public FileLoader(String directory){
		this.directory = directory;
	}
	
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
