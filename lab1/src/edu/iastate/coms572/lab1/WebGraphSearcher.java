package edu.iastate.coms572.lab1;

import java.util.HashSet;
import java.util.Set;

import edu.iastate.coms572.lab1.util.FileLoader;

public abstract class WebGraphSearcher {
	
	protected Set<String> openSet;
	
	protected Set<String> closedSet;
	
	protected String startNode;
	
	protected  String goalPattern;
	
	public WebGraphSearcher(String startNode, String goalPattern, FileLoader loader){
		this.openSet = new HashSet<String>();
		this.closedSet = new HashSet<String>();
		this.startNode = startNode;
		this.goalPattern = goalPattern;
		
	}
	
	
	
	
	protected SearchNode buildNodeFromFileName(String fileName){
		
		
		return null;
	}
	
	
	
}
