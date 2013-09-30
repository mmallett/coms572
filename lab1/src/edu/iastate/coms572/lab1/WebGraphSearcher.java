package edu.iastate.coms572.lab1;

import edu.iastate.coms572.lab1.util.FileLoader;

public abstract class WebGraphSearcher {
	
	protected String startNode;
	
	protected  String goalPattern;
	
	public WebGraphSearcher(String startNode, String goalPattern, FileLoader loader){
		this.startNode = startNode;
		this.goalPattern = goalPattern;
		
	}
	
	
	
	
	protected SearchNode buildNodeFromFileName(String fileName){
		
		
		return null;
	}
	
	
	
}
