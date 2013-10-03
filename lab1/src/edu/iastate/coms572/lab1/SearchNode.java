package edu.iastate.coms572.lab1;

import java.util.List;



/////////////////////////////////////////////////////////////////////////////////

//You'll need to design a Search node data structure.

//Note that the above code assumes there is a method called getHvalue()
//that returns (as a double) the heuristic value associated with a search node,
//a method called getNodeName() that returns (as a String)
//the name of the file (eg, "page7.html") associated with this node, and
//a (void) method called reportSolutionPath() that prints the path
//from the start node to the current node represented by the SearchNode instance.
public class SearchNode implements Comparable<SearchNode>
{
	/**
	 * name of this webpage
	 */
	private String name;
	
	/**
	 * node we traveled from to reach this node, NULL if this is the first node in a search
	 */
	private SearchNode parent;
	
	/**
	 * arcs linking to other pages
	 */
	private List<Link> links;
	
	/**
	 * String representation of the HTML page that this node contains
	 */
	private String rawData;
	
	/**
	 * heuristic value of this page calculated by getSuperHValue()
	 */
	private int hValue;
	
	/**
	 * constructs a new SearchNode
	 * @param name name of this webpage
	 * @param parent node that links to this page
	 * @param links list of all links this page contains
	 * @param data string containing the HTML of this page
	 */
	public SearchNode(String name, SearchNode parent, List<Link> links, String data) {
		this.name = name;
		this.parent = parent;
		this.links = links;
		this.rawData = data;
		
		hValue = 0;
	}

	/**
	 * print out a solution path from this node to the start node
	 */
	public void reportSolutionPath() {
		System.out.println(name);
		if(parent != null){
			parent.reportSolutionPath();
		}
	}

	/**
	 * get the name of this webpage
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * get the parent of this node, the node we traveled from
	 * to reach this node
	 * @return SearchNode parent of this node
	 */
	public SearchNode getParent(){
		return parent;
	}
	
	/**
	 * get the list of links this node contains
	 * @return list of links for this page
	 */
	public List<Link> getLinks(){
		return links;
	}
	
	/**
	 * get the HTML contained in this page
	 * @return String containing HTML for the page
	 */
	public String getRawData(){
		return rawData;
	}
	
	/**
	 * set the heuristic value for this web page
	 * @param hValue
	 */
	public void setHValue(int hValue){
		this.hValue = hValue;
	}
	
	/**
	 * get the heuristic value of this webpage
	 * @return
	 */
	public int getHValue(){
		return hValue;
	}

	@Override
	public int compareTo(SearchNode o) {
		//need to order such that the greatest h value is at the head of the queue
		if(this.hValue > o.getHValue()){
			return -1;
		}
		else if(this.hValue < o.getHValue()){
			return 1;
		}
		return 0;
	}

}

