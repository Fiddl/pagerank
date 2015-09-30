package edu.stevens.cs549.hadoop.pagerank;

import java.io.IOException;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;

public class InitMapper extends Mapper<LongWritable, Text, Text, Text> {

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException,
			IllegalArgumentException {
		String line = value.toString(); // Converts Line to a String
		/* 
		 * 	sample input:	8:	4 5 1 2 7
		 * 	input:	node: adj list
		 *  emit:	echo the input, since it is already in adjacency list format.
		 */
		String[] sections = line.split(":");
		String outKey = sections[0];
		String outVal = (sections.length < 2) ? "" : sections[1]; // If there are no links return an empty string.
		
		context.write(new Text(outKey), new Text(outVal));

	}

}
