package edu.iastate.coms572.lab1;

/**
 * Defines all possible search types supported
 * by this project
 * @author mmallett
 *
 */
public enum SearchType {

	BFS("breadth"),
	DFS("depth"),
	BEST("best"),
	BEAM("beam");
	
	private final String value;

	SearchType(String value){
		this.value = value;
	}
	
	@Override
	public String toString(){
		return value;
	}
}
