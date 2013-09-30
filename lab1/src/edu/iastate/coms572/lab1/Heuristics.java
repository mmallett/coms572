package edu.iastate.coms572.lab1;

import java.util.StringTokenizer;


public class Heuristics {
	
	public static final String KEYWORD = "QUERY"; 
	
	public static final int PAGE_MATCH_BIAS = 1;
	public static final int LINK_MATCH_BIAS = 2;
	public static final int CONSECUTIVE_BIAS = 3;

	public static int superHValue(SearchNode node, Link link){
		
		return matchesOnPageHValue(node) + matchesInLinkHValue(link) + consecutiveWordsHValue(link);
	}
	
	public static int matchesOnPageHValue(SearchNode node){
		
		StringTokenizer tokenizer = new StringTokenizer(node.getRawData());
		
		int count = 0;
		
		while(tokenizer.hasMoreTokens()){
			String token = tokenizer.nextToken();
			count += (token.contains(KEYWORD)) ? 1 : 0;
		}
		
		return count * PAGE_MATCH_BIAS;
	}
	
	public static int matchesInLinkHValue(Link link){
		
		StringTokenizer tokenizer = new StringTokenizer(link.getData());
		
		int count = 0;
		
		while(tokenizer.hasMoreTokens()){
			String token = tokenizer.nextToken();
			count += (token.contains(KEYWORD)) ? 1 : 0;
		}
		
		return count * LINK_MATCH_BIAS;
	}
	
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
