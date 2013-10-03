package edu.iastate.coms572.lab1;

import java.util.HashSet;
import java.util.LinkedList;

// You should call this code as follows:
//
//   java WebSearch searchStrategyName
//   (or jview, in J++)
//
//   where <searchStrategyName> is one of {breadth, depth, best, beam}.

// The PARTIAL code below contains code for fetching and parsing
// the simple web pages we're using, as well as the fragments of
// a solution.  BE SURE TO READ ALL THE COMMENTS.

// Feel free to alter or discard whatever code you wish;
// the only requirement is that your main class be called WebSearch
// and that it accept the single argument described above
// (if you wish you can add additional OPTIONAL arguments, but they
// should default to the values "hardwired" in below).

public class WebSearch
{
	static LinkedList<SearchNode> OPEN; // Feel free to choose your own data structures for searching,
	static HashSet<String> CLOSED;      // and be sure to read documentation about them.

	static final boolean DEBUGGING = false; // When set, report what's happening.
	// WARNING: lots of info is printed.

	static int beamWidth = 2; // If searchStrategy = "beam",
	// limit the size of OPEN to this value.
	// The setSize() method in the Vector
	// class can be used to accomplish this.

	static final String START_NODE     = "page1.html";

	// A web page is a goal node if it includes 
	// the following string.
	static final String GOAL_PATTERN   = "QUERY1 QUERY2 QUERY3 QUERY4";

	public static void main(String args[])
	{ 
		if (args.length != 2)
		{
			System.out.println("You must provide the directoryName and searchStrategyName.  Please try again.");
		}
		else
		{
			String directoryName = args[0]; // Read the search strategy to use.
			String searchStrategyName = args[1]; // Read the search strategy to use.
			
			FileLoader loader = new FileLoader(directoryName);
			WebGraphSearcher searcher = SearcherFactory.getSearcher(START_NODE, GOAL_PATTERN, loader, searchStrategyName);
			
			if(searcher == null){
				System.out.println("Valid search types");
				for(SearchType type : SearchType.values()){
					System.out.println("\t"+ type.toString());
				}
				return;
			}
			
			searcher.search();
			
		}
	}
}