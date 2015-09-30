package edu.stevens.cs549.hadoop.pagerank;

import java.io.IOException;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;

public class IterMapper extends Mapper<LongWritable, Text, Text, Text> {

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException,
			IllegalArgumentException {
		double d = PageRankDriver.DECAY; // Decay factor
		String line = value.toString(); // Converts Line to a String
		String[] sections = line.split("\t"); // Splits it into two parts. Part 1: node+rank | Part 2: adj list

		if (sections.length > 2) // Checks if the data is in the incorrect format
		{
			throw new IOException("Incorrect data format");
		}
		if (sections.length != 2) {
			return; // Skip pages without links
		}		
		
		/* 
		 * input:	node+rank	adj list
		 * emit key: adj vertex, value: computed weight.
		 * 
		 * Also emit the input adjacency list for this node!
		 * Put a marker on the string value to indicate it is an adjacency list.
		 */
		String outKey = sections[0].split("\\+")[0];
		String[] adjacentVertices = sections[1].trim().split("\\s+");
		int verticeCount = adjacentVertices.length;
		double computedWeight = d * (Double.valueOf(sections[0].split("\\+")[1]) / verticeCount); // decay * (pageRank/N)
		
		String pageRank = String.valueOf(computedWeight);
		context.write(new Text(outKey), new Text(pageRank)); // emit key: vertex, value: page rank
		
		String adjList = "|" + sections[1]; // Marker to indicate this value is an adjacency list
		context.write(new Text(outKey), new Text(adjList)); // emit key: vertex, value: its adjacency list
	}

}
