package edu.iastate.coms572.lab1;

/**
 * defines the interface to implement a web search algorithm upon
 * @author mmallett
 *
 */
public abstract class WebGraphSearcher {
	
	/**
	 * name of page to start the search from
	 */
	protected String startNode;
	
	/**
	 * pattern to search for to indicate a goal state
	 */
	protected String goalPattern;
	
	/**
	 * FileLoader pointing to the directory that the intranet is stored in
	 */
	protected FileLoader loader;
	
	/**
	 * number of nodes visited by this search
	 * it is the implementing class's responsibility to keep proper track of this
	 */
	protected int nodesVisited;
	
	/**
	 * create a new web graph searcher
	 * @param startNode the page to start the search from
	 * @param goalPattern pattern to search for in the search
	 * @param loader FileLoader pointing to the directory of the intranet to load pages from
	 */
	public WebGraphSearcher(String startNode, String goalPattern, FileLoader loader){
		this.startNode = startNode;
		this.goalPattern = goalPattern;
		this.loader = loader;
		
		nodesVisited = 0;
	}
	
	/**
	 * perform a search with this WebGraphSearcher
	 */
	public abstract void search();
	
}
