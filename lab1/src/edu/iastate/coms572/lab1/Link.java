package edu.iastate.coms572.lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Object representation of a link on a webpage.
 * @author mmallett
 *
 */
public class Link {
	
	/**
	 * the page the link is linking to
	 */
	private String dest;
	
	/**
	 * String that is inside of the link tag
	 */
	private String data;
	
	/**
	 * create a new Link with a given destination and data
	 * @param dest page destination for the link
	 * @param data string contained inside of the link tag
	 */
	private Link(String dest, String data){
		this.dest = dest;
		this.data = data;
	}
	
	/**
	 * returns the link destination
	 * @return string representing the destination of this link
	 */
	public String getDestination(){
		return dest;
	}
	
	/**
	 * get the text inside of the link
	 * @return string containing the text that appears on the page for the link
	 */
	public String getData(){
		return this.data;
	}
	
	/**
	 * Parse links from an HTML page
	 * @param s HTML string containing links
	 * @return list of Links contained in the page
	 */
	public static List<Link> buildLinkListFromString(String s){
		StringTokenizer tokenizer = new StringTokenizer(s);
		
		ArrayList<Link> linkList = new ArrayList<Link>();
		
		while(tokenizer.hasMoreTokens()){
		
			String token = tokenizer.nextToken();
			
			//found start of an anchor tag
			if(token.equalsIgnoreCase("<A")){
				tokenizer.nextToken(); //skip HREF
				tokenizer.nextToken(); //skip =
				
				String destination = tokenizer.nextToken();
				
				tokenizer.nextToken();
				
				token = tokenizer.nextToken();
				StringBuilder b = new StringBuilder();
				while(!token.equalsIgnoreCase("</A>")){
					b.append(token + " ");
					token = tokenizer.nextToken();
				}
				
				linkList.add(new Link(destination, b.toString()));
			}
		}
		
		return linkList;
	}
	
	/**
	 * whether this link contains the goal pattern
	 * @param goalPattern goal pattern to check against
	 * @return true if goal else false
	 */
	public boolean isGoal(Set<String> goalPattern){
		for(String item : goalPattern){
			if(!data.contains(item)){
				return false;
			}
		}
		
		return true;
	}
	
}
