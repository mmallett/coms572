package edu.iastate.coms572.lab1.search;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.PriorityQueue;

import edu.iastate.coms572.lab1.Link;
import edu.iastate.coms572.lab1.SearchNode;
import edu.iastate.coms572.lab1.Heuristics;
import edu.iastate.coms572.lab1.util.FileLoader;

public class BestFirstSearcher extends WebGraphSearcher{

	private PriorityQueue<SearchNode> queue;
	private HashSet<String> closed;
	
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
				
			//for(Link link : node.getLinks()){
			//	if(link.isGoal(goalSet)){
					System.out.println("Nodes visited: " + super.nodesVisited);
					//System.out.println(link.getDestination());
					node.reportSolutionPath();
					return;
			//	}
				
			}//end if
			
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
		

		
	}

}
