package edu.iastate.coms572.lab1;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Implements breadth first search on the abstract class WebGraphSearcher
 * @author mmallett
 *
 */
public class BfsSearcher extends WebGraphSearcher{

	/**
	 * List of neighboring arcs to explore
	 */
	private LinkedList<SearchNode> queue;
	
	/**
	 * Explored nodes are added to the closed set
	 */
	private HashSet<String> closed;
	
	/**
	 * Creates a new breadth first searcher
	 * @param startNode node to commence search from
	 * @param goalPattern string pattern that indicates a goal page
	 * @param loader FileLoader that is tied to the directory of the intranet for this search
	 */
	public BfsSearcher(String startNode, String goalPattern, FileLoader loader) {
		super(startNode, goalPattern, loader);
		
		queue = new LinkedList<SearchNode>();
		closed = new HashSet<String>();
		
	}
	
	@Override
	public void search() {
		
		closed.add(startNode);
		
		String contents = "";
		try {
			contents = loader.load(startNode);
		} catch (FileNotFoundException e) {
			System.err.println(e);
		}
		
		SearchNode start = new SearchNode(startNode, null, Link.buildLinkListFromString(contents), contents);
		queue.add(start);
		
		while(!queue.isEmpty()){
			SearchNode node = queue.remove();
			super.nodesVisited++;
			
			//check if goal state
			if(node.getRawData().contains(goalPattern)){
	
				System.out.println("Nodes visited: " + super.nodesVisited);
				node.reportSolutionPath();
				return;
				
			}//end if
			
			//add neighboring arcs to queue
			for(Link link : node.getLinks()){
				if(!closed.contains(link.getDestination())){
					closed.add(link.getDestination());
					
					try {
						contents = loader.load(link.getDestination());
					} catch (FileNotFoundException e) {
						contents = "";
					}
					
					SearchNode newNode = new SearchNode(link.getDestination(), node,
							Link.buildLinkListFromString(contents), contents);
					queue.add(newNode);
				}
				
			}
			
		}//end while
		
		System.out.println("Nodes visited: " + super.nodesVisited);
		System.out.println("Goal not found");
		
	}//end search()

}//end BfsSearcher
