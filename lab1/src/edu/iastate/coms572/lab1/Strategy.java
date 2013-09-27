package edu.iastate.coms572.lab1;

import java.util.List;

public abstract class Strategy {
	
	protected final StrategyType type;
	
	public final StrategyType getStrategyType(){
		return type;
	}
	
	public enum StrategyType{
		
		BFS("breadth"),
		DFS("depth"),
		BEST("best"),
		BEAM("beam");
		
		private final String value;

		StrategyType(String value){
			this.value = value;
		}	
	}
	
	public Strategy(StrategyType type){
		this.type = type;
	}
	
	public abstract SearchNode getNextNode(SearchNode currentNode, List<SearchNode> fringe);

}
