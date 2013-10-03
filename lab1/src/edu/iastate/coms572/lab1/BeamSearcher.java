package edu.iastate.coms572.lab1;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Implements beam search on the abstract class WebGraphSearcher. Beam width is defined
 * with the constant BEAM_WIDTH
 * @author mmallett
 *
 */
public class BeamSearcher extends WebGraphSearcher{
	
	/**
	 * Maximum size of the priority queue
	 */
	public static final int BEAM_WIDTH = 10;

	/**
	 * Potential arcs to explored are stored in this priority queue
	 * max size is BEAM_WIDTH
	 */
	private LinkedList<SearchNode> queue;
	
	/**
	 * Explored nodes are stored in the closed set
	 */
	private HashSet<String> closed;
	
	/**
	 * Creates a new beam searcher
	 * @param startNode node to commence search from
	 * @param goalPattern string pattern that indicates a goal page
	 * @param loader FileLoader that is tied to the directory of the intranet for this search
	 */
	public BeamSearcher(String startNode, String goalPattern, FileLoader loader) {
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
			
			//add high ranked arcs to queue
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
		
		//queue is not full
		if(queue.size() < BEAM_WIDTH){
			queue.add(node);
			Collections.sort(queue);
		}
		//queue is full
		else if(queue.get(BEAM_WIDTH -1 ).getHValue() < node.getHValue()){ 
			queue.remove(BEAM_WIDTH - 1);
			queue.add(node);
			Collections.sort(queue);
		}
		
	}//end enqueue

}
