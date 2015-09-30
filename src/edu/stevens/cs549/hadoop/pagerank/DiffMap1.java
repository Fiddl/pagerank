package edu.stevens.cs549.hadoop.pagerank;

import java.io.IOException;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;

public class DiffMap1 extends Mapper<LongWritable, Text, Text, Text> {

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException,
			IllegalArgumentException {
		String line = value.toString(); // Converts Line to a String
		String[] sections = line.split("\t"); // Splits each line
		if (sections.length > 2) // checks for incorrect data format
		{
			throw new IOException("Incorrect data format");
		}
		/**
		 *  sample input:	8+0.57	4 5 1 2 
		 *  input:	node+rank	adj list
		 *  emit: key:node, value:rank
		 */
		String outKey = sections[0].split("\\+")[0];
		String outVal = sections[0].split("\\+")[1];
		
		context.write(new Text(outKey), new Text(outVal));

	}

}
