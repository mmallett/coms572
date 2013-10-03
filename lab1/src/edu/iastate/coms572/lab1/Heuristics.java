package edu.iastate.coms572.lab1;

import java.util.StringTokenizer;

/**
 * Heuristic functions created to score different nodes and create a priority of
 * which should be explored first
 * @author mmallett
 *
 */
public class Heuristics {
	
	/**
	 * keyword to look for in the nodes and arcs
	 */
	public static final String KEYWORD = "QUERY"; 
	
	/**
	 * relative weight of query word hits in the node as a whole
	 */
	public static final int PAGE_MATCH_BIAS = 1;
	
	/**
	 * relative weight of query word hits inside of an arc
	 */
	public static final int LINK_MATCH_BIAS = 2;
	
	/**
	 * relative weight of consecutive and sequential query word hits inside of an arc
	 */
	public static final int CONSECUTIVE_BIAS = 3;

	/**
	 * combines the 3 other heuristics into one heuristic
	 * @param node node to evaluate heuristic for
	 * @param link arc to evaulaute heuristic for
	 * @return heuristic value of the 3 heuristics combined
	 */
	public static int superHValue(SearchNode node, Link link){
		
		return matchesOnPageHValue(node) + matchesInLinkHValue(link) + consecutiveWordsHValue(link);
	}
	
	/**
	 * computes a heuristic based on the number of keywords on the page, using
	 * the relative weight of PAGE_MATCH_BIAS
	 * @param node node to evaluate heuristic for
	 * @return heuristic value of the node
	 */
	public static int matchesOnPageHValue(SearchNode node){
		
		StringTokenizer tokenizer = new StringTokenizer(node.getRawData());
		
		int count = 0;
		
		while(tokenizer.hasMoreTokens()){
			String token = tokenizer.nextToken();
			count += (token.contains(KEYWORD)) ? 1 : 0;
		}
		
		return count * PAGE_MATCH_BIAS;
	}
	
	/**
	 * computes a heuristic based on the number of keywords in a link, using
	 * the relative weight of LINK_MATCH_BIAS
	 * @param link link to evaluate heuristic for
	 * @return heuristic value of the link
	 */
	public static int matchesInLinkHValue(Link link){
		
		StringTokenizer tokenizer = new StringTokenizer(link.getData());
		
		int count = 0;
		
		while(tokenizer.hasMoreTokens()){
			String token = tokenizer.nextToken();
			count += (token.contains(KEYWORD)) ? 1 : 0;
		}
		
		return count * LINK_MATCH_BIAS;
	}
	
	/**
	 * computes a heuristic based on the number of consecutive keywords in
	 * correct seqeunce in a link, using the relative weight of CONSECUTIVE_BIAS
	 * @param link link to evaluate heuristic for
	 * @return heuristic value of the link
	 */
	public static int consecutiveWordsHValue(Link link){
		
		int lastPageNum = -1;
		int count = 0;
		
		StringTokenizer tokenizer = new StringTokenizer(link.getData());
		
		while(tokenizer.hasMoreTokens()){
			String token = tokenizer.nextToken();
			if(token.contains(KEYWORD)){
				int pageNum = Integer.parseInt(token.replaceAll(KEYWORD, ""));
				count += (pageNum == lastPageNum + 1) ? 1 : 0;
				lastPageNum = pageNum;
			}
			else{
				lastPageNum = -1;
			}
		}
		
		return count * CONSECUTIVE_BIAS;
	}
	
}
