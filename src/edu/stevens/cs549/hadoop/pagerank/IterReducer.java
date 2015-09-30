package edu.stevens.cs549.hadoop.pagerank;

import java.io.*;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;

public class IterReducer extends Reducer<Text, Text, Text, Text> {
	
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		double d = PageRankDriver.DECAY; // Decay factor
		double sumOfPageRanks = 0;
		double updatedPageRank;
		String adjacencyList = "";
		/* 
		 * input: node	rank	(or)	node	adj list
		 * emit key:node+rank, value: adjacency list
		 * 
		 * Use PageRank algorithm to compute rank from weights contributed by incoming edges.
		 * Remember that one of the values will be marked as the adjacency list for the node.
		 */
		
		for (Text val : values) {
			if (val.toString().startsWith("|")) {
				adjacencyList = val.toString();
			}
			else {
			sumOfPageRanks += Double.valueOf(val.toString()); // sum of contributing edges
			}
		}
		
		updatedPageRank = sumOfPageRanks + (1-d);
		
		context.write(new Text(key.toString() + "+" + updatedPageRank), new Text(adjacencyList));
	}
}
