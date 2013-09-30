package edu.iastate.coms572.lab1.search;

import edu.iastate.coms572.lab1.util.FileLoader;

public abstract class WebGraphSearcher {
	
	protected String startNode;
	
	protected String goalPattern;
	
	protected FileLoader loader;
	
	protected int nodesVisited;
	
	public WebGraphSearcher(String startNode, String goalPattern, FileLoader loader){
		this.startNode = startNode;
		this.goalPattern = goalPattern;
		this.loader = loader;
		
		nodesVisited = 0;
	}
	
	public abstract void search();
	
}
