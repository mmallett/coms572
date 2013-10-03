package edu.iastate.coms572.lab1;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Implements beam search from the abstract class WebGraphSearcher
 * @author mmallett
 *
 */
public class BestFirstSearcher extends WebGraphSearcher{

	/**
	 * Unbounded heuristic weighted queue of potential arcs to explore
	 */
	private PriorityQueue<SearchNode> queue;
	
	/**
	 * Explored nodes are stored in the closed set
	 */
	private HashSet<String> closed;
	
	/**
	 * Creates a new best first searcher
	 * @param startNode node to commence search from
	 * @param goalPattern string pattern that indicates a goal page
	 * @param loader FileLoader that is tied to the directory of the intranet for this search
	 */
	public BestFirstSearcher(String startNode, String goalPattern,
			FileLoader loader) {
		super(startNode, goalPattern, loader);
		
		queue = new PriorityQueue<SearchNode>();
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
					int hvalue = Heuristics.superHValue(node, link);
					newNode.setHValue(hvalue);
					queue.add(newNode);
				}
				
			}
			
		}//end while
		
		System.out.println("Nodes visited: " + super.nodesVisited);
		System.out.println("Goal not found");
		

		
	}//end search()

}
