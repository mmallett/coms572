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
	private String name;
	
	private SearchNode parent;
	
	private List<Link> links;
	
	private String rawData;
	
	private int hValue;
	
	public SearchNode(String name, SearchNode parent, List<Link> links, String data) {
		this.name = name;
		this.parent = parent;
		this.links = links;
		this.rawData = data;
		
		hValue = 0;
	}

	public void reportSolutionPath() {
		System.out.println(name);
		if(parent != null){
			parent.reportSolutionPath();
		}
	}

	public String getName() {
		return name;
	}
	
	public SearchNode getParent(){
		return parent;
	}
	
	public List<Link> getLinks(){
		return links;
	}
	
	public String getRawData(){
		return rawData;
	}
	
	public void setHValue(int hValue){
		this.hValue = hValue;
	}
	
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

/////////////////////////////////////////////////////////////////////////////////

