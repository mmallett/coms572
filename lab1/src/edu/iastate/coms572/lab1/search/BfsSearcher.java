package edu.iastate.coms572.lab1.search;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;

import edu.iastate.coms572.lab1.Link;
import edu.iastate.coms572.lab1.SearchNode;
import edu.iastate.coms572.lab1.WebGraphSearcher;
import edu.iastate.coms572.lab1.util.FileLoader;

public class BfsSearcher extends WebGraphSearcher{

	private LinkedList<SearchNode> queue;
	private HashSet<String> closed;
	
	private HashSet<String> goalSet;
	
	public BfsSearcher(String startNode, String goalPattern, FileLoader loader) {
		super(startNode, goalPattern, loader);
		
		queue = new LinkedList<SearchNode>();
		closed = new HashSet<String>();
		
		goalSet = new HashSet<String>();
		for(String item : goalPattern.split(" ")){
			goalSet.add(item);
		}
	}
	
	public void search() {
		
		closed.add(startNode);
		
		String contents = "";
		try {
			contents = loader.load(startNode);
		} catch (FileNotFoundException e) {
			System.err.println(e);
		}
		
		SearchNode start = new SearchNode(startNode, null, Link.buildLinkListFromString(contents));
		queue.add(start);
		
		while(!queue.isEmpty()){
			SearchNode node = queue.remove();
			super.nodesVisited++;
			
			//check if goal state
			for(Link link : node.getLinks()){
				if(link.isGoal(goalSet)){
					System.out.println("Nodes visited: " + super.nodesVisited);
					System.out.println(link.getDestination());
					node.reportSolutionPath();
					return;
				}
				
			}//end for
			
			for(Link link : node.getLinks()){
				if(!closed.contains(link.getDestination())){
					closed.add(link.getDestination());
					
					try {
						contents = loader.load(link.getDestination());
					} catch (FileNotFoundException e) {
						contents = "";
					}
					
					SearchNode newNode = new SearchNode(link.getDestination(), node,
							Link.buildLinkListFromString(contents));
					queue.add(newNode);
				}
				
			}
			
		}//end while
		
		System.out.println("Nodes visited: " + super.nodesVisited);
		System.out.println("Goal not found");
		
	}//end search()

}//end BfsSearcher