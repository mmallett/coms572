package edu.iastate.coms572.lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Link {

	//private static String testitlul = " w18 w71 QUERY3 w73 QUERY2 w5 w48 w18 w41 w9 QUERY3 w23 w75 w4 w51 w17 QUERY2 w2 w26 w13 w7 w3 w76 w11 w28 <A HREF = page33.html > w92 w18 w6 w14 w81 </A> <A HREF = page54.html > w75 w5 </A> w87 w4 w3 <A HREF = page45.html > w86 QUERY1 w48 w12 w13 w15 </A> w85 w99 <A HREF = page61.html > QUERY1 QUERY2 </A> w13 w7 w9 w12 w15 w6 ";
	
	private String dest;
	
	private String data;
	
	private Link(String dest, String data){
		this.dest = dest;
		this.data = data;
	}
	
	public String getDestination(){
		return dest;
	}
	
	public String getData(){
		return this.data;
	}
	
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
	
	public boolean isGoal(Set<String> goalPattern){
		//System.out.println("Checking goal of link: " + this.toString() + " goal: " + goalPattern.toString());
		for(String item : goalPattern){
			if(!data.contains(item)){
				return false;
			}
		}
		
		return true;
	}
	
	
	/*@Override
	public String toString(){
		StringBuilder b = new StringBuilder();
		b.append(dest + " ");
		for(String item : data){
			b.append(item + " ");
		}
		return b.toString();
	}/*
	//testing ( it worked =] )
	/*
	public static void main(String args[]){
		
		for(Link link : buildLinkListFromString(testitlul)){
			StringBuilder sb = new StringBuilder();
			sb.append(link.getDestination() + " ");
			for(String data : link.getData()){
				sb.append(data + " ");
			}
			System.out.println(sb.toString());
		}
	}*/
	
}
