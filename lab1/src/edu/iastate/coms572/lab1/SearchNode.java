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
public class SearchNode
{
	private String name;
	
	private SearchNode parent;
	
	private List<Link> links;
	
	public SearchNode(String name, SearchNode parent, List<Link> links) {
		this.name = name;
		this.parent = parent;
		this.links = links;
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

}

/////////////////////////////////////////////////////////////////////////////////

