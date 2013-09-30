package edu.iastate.coms572.lab1.search;

import edu.iastate.coms572.lab1.util.FileLoader;

public class SearcherFactory {
	
	public static WebGraphSearcher getSearcher(String startNode, String goalPattern,
			FileLoader loader, String searchType){
		
		if(searchType.equalsIgnoreCase(SearchType.BFS.toString())){
			return new BfsSearcher(startNode, goalPattern, loader);
		}
		else if(searchType.equalsIgnoreCase(SearchType.DFS.toString())){
			return new DfsSearcher(startNode, goalPattern, loader);
		}
		return null;
	}

}
