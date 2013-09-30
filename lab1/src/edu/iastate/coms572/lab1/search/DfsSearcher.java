package edu.iastate.coms572.lab1.search;

import java.io.FileNotFoundException;
import java.util.HashSet;

import edu.iastate.coms572.lab1.util.FileLoader;

public class DfsSearcher extends WebGraphSearcher{

	private HashSet<String> closed;
	
	public DfsSearcher(String startNode, String goalPattern, FileLoader loader) {
		super(startNode, goalPattern, loader);
		
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
		
		boolean goalFound = recursiveSearch(start);
		/*
		
		
		stack.push(start);
		
		while(!stack.isEmpty()){
			SearchNode node = stack.pop();
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
							Link.buildLinkListFromString(contents), contents);
					stack.push(newNode);
				}
				
			}
			
		}//end while
		*/
		if(!goalFound){
			System.out.println("Nodes visited: " + super.nodesVisited);
			System.out.println("Goal not found");
		}
		
	}//end search()
	
	public boolean recursiveSearch(SearchNode node){
		
		super.nodesVisited++;
		
		//goal check
		if(node.getRawData().contains(goalPattern)){
			System.out.println("Nodes visited " + super.nodesVisited);
			node.reportSolutionPath();
			return true;
		}
		
		//recursively search
		//break out if a child found the goal
		for(Link link : node.getLinks()){
			if(!closed.contains(link.getDestination())){
				closed.add(link.getDestination());
				String content;
				try {
					content = loader.load(link.getDestination());
				} catch (FileNotFoundException e) {
					content = "";
				}
				SearchNode newNode = new SearchNode(link.getDestination(), node, Link.buildLinkListFromString(content), content);
				boolean childFoundGoal = recursiveSearch(newNode);
				if(childFoundGoal){
					return true;
				}
			}
		}
		
		return false;
	}

}//end BfsSearcher
