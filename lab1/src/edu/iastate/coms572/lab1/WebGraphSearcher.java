package edu.iastate.coms572.lab1;

import java.util.HashSet;
import java.util.Set;

import edu.iastate.coms572.lab1.util.FileLoader;

public abstract class WebGraphSearcher {
	
	private Set<String> openSet;
	
	private Set<String> closedSet;
	
	private String startNode;
	
	private String goalPattern;
	
	public WebGraphSearcher(String startNode, String goalPattern, FileLoader loader, Strategy strategy){
		this.openSet = new HashSet<String>();
		this.closedSet = new HashSet<String>();
		this.startNode = startNode;
		this.goalPattern = goalPattern;
		
	}
	
	
	
}
