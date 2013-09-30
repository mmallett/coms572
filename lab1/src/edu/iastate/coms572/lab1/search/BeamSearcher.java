package edu.iastate.coms572.lab1.search;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.PriorityQueue;

import edu.iastate.coms572.lab1.Heuristics;
import edu.iastate.coms572.lab1.Link;
import edu.iastate.coms572.lab1.SearchNode;
import edu.iastate.coms572.lab1.util.FileLoader;

public class BeamSearcher extends WebGraphSearcher{
	
	public static final int BEAM_WIDTH = 10;

	private PriorityQueue<SearchNode> queue;
	private HashSet<String> closed;
	
	public BeamSearcher(String startNode, String goalPattern, FileLoader loader) {
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
					enqueue(newNode);
				}
				
			}
			
		}//end while
		
		System.out.println("Nodes visited: " + super.nodesVisited);
		System.out.println("Goal not found");

		
	}//end search()
	

	/*
	 * size controlled push to queue
	 */
	private void enqueue(SearchNode node){
		
		if(queue.size() < BEAM_WIDTH){
			queue.add(node);
		}
		else{ //queue is full
			//have to check if the node is better than an existing queued node
			//no easy way to do it with priorityqueue unfortunately..
			PriorityQueue<SearchNode> tmp = new PriorityQueue<SearchNode>();
			int min = node.getHValue();
			while(!queue.isEmpty()){
				SearchNode popped = queue.remove();
				min = Math.min(min, popped.getHValue());
				tmp.add(popped);
			}
			if(min == node.getHValue()){
				queue.addAll(tmp);
				tmp.clear();
			}
			else{
				while(!tmp.isEmpty()){
					SearchNode popped = tmp.remove();
					if(!(popped.getHValue() == min)){
						queue.add(popped);
					}
					queue.add(node);
				}
			}
			
		}
		
	}//end enqueue

}
