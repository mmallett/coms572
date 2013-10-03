package edu.iastate.coms572.lab1;

/**
 * Constructs a concrete implementation of WebGraphSearcher
 * @author mmallett
 *
 */
public class SearcherFactory {
	
	/**
	 * Constructs a concrete WebGraphSearcher using the given arguments and chosen from
	 * valid types described in SearchType with String searchType
	 * @param startNode page name that the search should start on
	 * @param goalPattern pattern to look for to indicate goal page
	 * @param loader FileLoader pointing to the directory of the intranet (ex /home/matt/intranet7/ you MUST include the final slash)
	 * @param searchType string mapping to one of the types defined by SearchType
	 * @return WebGraphSearcher object based on above arguments
	 */
	public static WebGraphSearcher getSearcher(String startNode, String goalPattern,
			FileLoader loader, String searchType){
		
		if(searchType.equalsIgnoreCase(SearchType.BFS.toString())){
			return new BfsSearcher(startNode, goalPattern, loader);
		}
		else if(searchType.equalsIgnoreCase(SearchType.DFS.toString())){
			return new DfsSearcher(startNode, goalPattern, loader);
		}
		else if(searchType.equalsIgnoreCase(SearchType.BEST.toString())){
			return new BestFirstSearcher(startNode, goalPattern, loader);
		}
		else if(searchType.equalsIgnoreCase(SearchType.BEAM.toString())){
			return new BeamSearcher(startNode, goalPattern, loader);
		}
		return null;
	}

}
